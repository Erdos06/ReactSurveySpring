package com.react.survey.mappers.answer;

import com.react.survey.dtos.answer.UserSurveyDto;
import com.react.survey.entities.answer.UserSurvey;
import com.react.survey.mappers.survey.QuestionMapper;
import com.react.survey.mappers.survey.SurveyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SurveyMapper.class,  QuestionMapper.class})
public interface UserSurveyMapper {
    @Mapping(source = "survey", target = "surveyDto")
    @Mapping(source = "lastQuestion", target = "lastQuestionDto")
    UserSurveyDto toUserSurveyDto(UserSurvey userSurvey);
    UserSurvey toUserSurvey(UserSurveyDto userSurveyDto);
}
