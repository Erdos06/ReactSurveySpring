package com.react.survey.services;

import com.react.survey.dtos.answer.AnswerDto;
import com.react.survey.dtos.survey.SurveyDto;
import com.react.survey.entities.answer.UserSurvey;
import com.react.survey.entities.answer.userAnswer.UserAnswer;
import com.react.survey.entities.answer.userAnswer.UserAnswerTextArea;
import com.react.survey.entities.user.User;
import com.react.survey.mappers.survey.QuestionMapper;
import com.react.survey.mappers.survey.SurveyMapper;
import com.react.survey.repositories.answer.UserSurveyRepository;
import com.react.survey.repositories.survey.QuestionRepository;
import jakarta.transaction.Transactional;
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
}
