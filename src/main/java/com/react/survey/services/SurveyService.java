package com.react.survey.services;

import com.react.survey.dtos.user.UserDTO;
import com.react.survey.entities.user.User;
import com.react.survey.mappers.survey.SurveyMapper;
import com.react.survey.mappers.user.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.react.survey.dtos.survey.SurveyDTO;
import com.react.survey.entities.survey.Option;
import com.react.survey.entities.survey.Question;
import com.react.survey.entities.survey.Survey;
import com.react.survey.repositories.SurveyRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final SurveyMapper surveyMapper;
    private final UserMapper userMapper;

    public List<SurveyDTO> getAllSurveys(){
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

    public void changeSurvey(int id, SurveyDTO surveyDto){
        Survey survey = surveyRepository.findById(id).orElseThrow(() -> new RuntimeException("Survey not found"));
        surveyMapper.updateSurveyFromDto(surveyDto, survey);
        surveyRepository.save(survey);
    }

    public SurveyDTO createSurvey(SurveyDTO dto){
        Survey survey = surveyMapper.toSurvey(dto);
        User user = getCurrentUser();
        survey.setAuthor(user.getUsername());
        surveyRepository.save(survey);
        return surveyMapper.toSurveyDto(survey);
    }

    private User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDto = (UserDTO) authentication.getPrincipal();
        User user = userMapper.toUser(userDto);
        return user;
    }
}
