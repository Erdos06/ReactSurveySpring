package com.react.survey.mappers.survey;

import com.react.survey.dtos.survey.SurveyDto;
import com.react.survey.entities.survey.Survey;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = QuestionMapper.class)
public interface SurveyMapper {

    @Mapping(source = "surveyId", target = "surveyId")
    Survey toSurvey(SurveyDto dto);

    @Mapping(source = "surveyId", target = "surveyId")
    SurveyDto toSurveyDto(Survey survey);

    void updateSurveyFromDto(SurveyDto dto, @MappingTarget Survey survey);

    @AfterMapping
    default void linkQuestions(@MappingTarget Survey survey) {
        if (survey.getQuestions() != null) {
            survey.getQuestions().forEach(question -> question.setSurvey(survey));
        }
    }
}

