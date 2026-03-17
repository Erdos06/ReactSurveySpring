package com.react.survey.services;

import com.react.survey.dtos.answer.AnswerDto;
import com.react.survey.dtos.survey.SurveyDto;
import com.react.survey.entities.answer.UserSurvey;
import com.react.survey.entities.user.User;
import com.react.survey.mappers.survey.SurveyMapper;
import com.react.survey.repositories.answer.UserSurveyRepository;
import com.react.survey.repositories.survey.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.react.survey.entities.survey.Survey;
import com.react.survey.repositories.survey.SurveyRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final UserSurveyRepository userSurveyRepository;

    private final UserService userService;
    private final SurveyMapper surveyMapper;

    public List<SurveyDto> getAllSurveys(){
        List<Survey> surveys = surveyRepository.findAll();

        return surveys.stream()
                .map(surveyMapper::toSurveyDto)
                .toList();
    }

    public Survey getSurveyById(int id){
        return surveyRepository.findById(id).get();
    }

    public void save(Survey survey){
        surveyRepository.save(survey);
    }

    public void deleteSurvey(int id){
        surveyRepository.deleteById(id);
    }

    public void changeSurvey(int id, SurveyDto surveyDto){
        Survey survey = surveyRepository.findById(id).orElseThrow(() -> new RuntimeException("Survey not found"));
        surveyMapper.updateSurveyFromDto(surveyDto, survey);
        surveyRepository.save(survey);
    }

    public SurveyDto createSurvey(SurveyDto dto){
        Survey survey = surveyMapper.toSurvey(dto);
        User user = userService.getCurrentUser();
        survey.setAuthor(user.getUsername());
        surveyRepository.save(survey);
        return surveyMapper.toSurveyDto(survey);
    }

    public UserSurvey startSurvey(int surveyId){
        UserSurvey userSurvey = new UserSurvey();
        userSurvey.setUser(userService.getCurrentUser());
        userSurvey.setSurvey(surveyRepository.findById(surveyId).get());
        userSurvey.setLastQuestion(questionRepository.findById(surveyRepository.findFirstQuestionIdBySurveyId(surveyId).get()).get());
        return userSurveyRepository.save(userSurvey);
    }

    public boolean surveyContainsQuestion(int surveyId, AnswerDto answerDto){
        return surveyRepository.questionExistsInSurvey(surveyId, answerDto.getQuestionDto().getQuestionId());
    }
    public boolean questionContainsOption(AnswerDto answerDto){
        return surveyRepository.optionExistsInQuestion(answerDto.getQuestionDto().getQuestionId(), answerDto.getOptionDto().getOptionId());
    }

    public void answerSurvey(int surveyId, AnswerDto answerDto){

    }
}
