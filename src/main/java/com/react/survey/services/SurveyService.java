package com.react.survey.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.react.survey.dtos.SurveyDTO;
import com.react.survey.entities.Option;
import com.react.survey.entities.Question;
import com.react.survey.entities.Survey;
import com.react.survey.repositories.SurveyRepository;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SurveyService {
    SurveyRepository surveyRepository;
    @Autowired
    public void setSurveyRepository(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public List<Survey> getAllSurveys(){
        return surveyRepository.findAll();
    }

    public Survey getSurveyById(long id){
        return surveyRepository.findById(id).get();
    }

    public void save(Survey survey){
        surveyRepository.save(survey);
    }

    public void deleteSurvey(Long id){
        surveyRepository.deleteById(id);
    }

    public Survey createSurvey(SurveyDTO dto){
        Survey survey = new Survey();
        survey.setTitle(dto.getTitle());
        survey.setDescription(dto.getDescription());
        survey.setAuthor(dto.getAuthor());
        survey.setCreatedAt(Date.from(Instant.now()));

        if(dto.getQuestions() != null) {
            survey.setQuestions(
                    dto.getQuestions().stream().map(questionDTO -> {
                        Question question = new Question();
                        question.setText(questionDTO.getText());
                        question.setType(questionDTO.getType());
                        question.setRequired(questionDTO.isRequired());
                        if(questionDTO.getOptions() != null) {
                            question.setOptions(questionDTO.getOptions().stream().map(optionDTO ->  {
                                System.out.println(optionDTO);
                                Option option = new Option();
                                option.setOptionText(optionDTO.getOptionText());
                                return option;
                            }).toList());
                        }

                        return question;
                    }).collect(Collectors.toList())
            );
        }

        return surveyRepository.save(survey);
    }
}
