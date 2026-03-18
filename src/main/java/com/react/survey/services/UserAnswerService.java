package com.react.survey.services;

import com.react.survey.dtos.answer.AnswerDto;
import com.react.survey.dtos.answer.UserSurveyDto;
import com.react.survey.dtos.survey.OptionDto;
import com.react.survey.entities.answer.SurveyStatus;
import com.react.survey.entities.answer.UserSurvey;
import com.react.survey.entities.answer.userAnswer.UserAnswer;
import com.react.survey.entities.answer.userAnswer.UserAnswerOption;
import com.react.survey.entities.answer.userAnswer.UserAnswerTextArea;
import com.react.survey.entities.survey.Option;
import com.react.survey.entities.survey.Question;
import com.react.survey.entities.survey.Survey;
import com.react.survey.entities.user.User;
import com.react.survey.mappers.answer.UserSurveyMapper;
import com.react.survey.repositories.answer.UserAnswerOptionRepository;
import com.react.survey.repositories.answer.UserAnswerRepository;
import com.react.survey.repositories.answer.UserAnswerTextAreaRepository;
import com.react.survey.repositories.answer.UserSurveyRepository;
import com.react.survey.repositories.survey.OptionRepository;
import com.react.survey.repositories.survey.QuestionRepository;
import com.react.survey.repositories.survey.SurveyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAnswerService {
    private final UserSurveyRepository userSurveyRepository;
    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final UserService userService;
    private final UserAnswerRepository userAnswerRepository;
    private final UserAnswerTextAreaRepository userAnswerTextAreaRepository;
    private final UserAnswerOptionRepository userAnswerOptionRepository;
    private final UserSurveyMapper userSurveyMapper;

    public UserSurveyDto startSurvey(int surveyId) {
        User currentUser = userService.getCurrentUser();
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));

        return userSurveyMapper.toUserSurveyDto(userSurveyRepository.findByUserAndSurvey(currentUser, survey)
                .orElseGet(() -> {
                    UserSurvey userSurvey = new UserSurvey();
                    userSurvey.setUser(currentUser);
                    userSurvey.setSurvey(survey);

                    Integer firstQuestionId = surveyRepository.findFirstQuestionIdBySurveyId(surveyId)
                            .orElseThrow(() -> new IllegalStateException("В опросе нет вопросов"));

                    Question firstQuestion = questionRepository.findById(firstQuestionId)
                            .orElseThrow(() -> new IllegalStateException("Вопрос не найден"));

                    userSurvey.setLastQuestion(firstQuestion);
                    userSurvey.setStatus(SurveyStatus.NOT_STARTED);

                    return userSurveyRepository.save(userSurvey);
                }));
    }

    public boolean surveyContainsQuestion(int surveyId, AnswerDto answerDto){
        return surveyRepository.questionExistsInSurvey(surveyId, answerDto.getQuestionDto().getQuestionId());
    }
    public boolean questionContainsOption(AnswerDto answerDto){
        boolean result = true;
        for(OptionDto optionDto : answerDto.getOptionDtos()){
            result &= surveyRepository.optionExistsInQuestion(answerDto.getQuestionDto().getQuestionId(), optionDto.getOptionId());
        }
        return result;
    }

    @Transactional
    public void answerSurvey(int surveyId, AnswerDto answerDto) {
        if (!surveyContainsQuestion(surveyId, answerDto)) {
            throw new IllegalArgumentException("Question does not belong to this survey");
        }

        User currentUser = userService.getCurrentUser();
        String type = answerDto.getQuestionDto().getType();
        boolean isTextArea = "textarea".equalsIgnoreCase(type);
        Survey survey = surveyRepository.getReferenceById(surveyId);
        Question currentQuestion = questionRepository.getReferenceById(answerDto.getQuestionDto().getQuestionId());

        UserAnswer userAnswer = userAnswerRepository
                .findByUserAndSurveyAndQuestion(currentUser, survey, currentQuestion)
                .orElseGet(() -> {
                    // Если не нашли — создаем новый
                    UserAnswer newAns = new UserAnswer();
                    newAns.setUser(currentUser);
                    newAns.setSurvey(survey);
                    newAns.setQuestion(currentQuestion);
                    return userAnswerRepository.save(newAns);
                });

        if (isTextArea) {
            userAnswerTextAreaRepository.deleteByUserAnswer(userAnswer);

            UserAnswerTextArea textArea = new UserAnswerTextArea();
            textArea.setUserAnswer(userAnswer);
            textArea.setTextAreaAnswer(answerDto.getTextAreaAnswer());
            userAnswerTextAreaRepository.save(textArea);
        } else {
            if (answerDto.getOptionDtos() == null || answerDto.getOptionDtos().isEmpty()) {
                throw new IllegalArgumentException("No options provided for choice-type question");
            }

            userAnswerOptionRepository.deleteByUserAnswer(userAnswer);
            for (OptionDto optDto : answerDto.getOptionDtos()) {
                UserAnswerOption uao = new UserAnswerOption();
                uao.setUserAnswer(userAnswer);
                uao.setOption(optionRepository.getReferenceById(optDto.getOptionId()));
                userAnswerOptionRepository.save(uao);
            }
        }

        UserSurvey session = userSurveyRepository.findByUserAndSurvey(currentUser, surveyRepository.getReferenceById(surveyId))
                .orElseThrow(() -> new IllegalStateException("Session not found"));

        session.setLastQuestion(userAnswer.getQuestion());
        session.setStatus(SurveyStatus.IN_PROGRESS);
        userSurveyRepository.save(session);
    }
}
