package com.react.survey.mappers.survey;

import com.react.survey.dtos.survey.SurveyDTO;
import com.react.survey.entities.survey.Survey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = QuestionMapper.class)
public interface SurveyMapper {

    @Mapping(source = "surveyId", target = "surveyId")
    Survey toSurvey(SurveyDTO dto);

    @Mapping(source = "surveyId", target = "surveyId")
    SurveyDTO toSurveyDto(Survey survey);

    void updateSurveyFromDto(SurveyDTO dto, @MappingTarget Survey survey);
}

