package com.react.survey.dtos.answer;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.react.survey.dtos.survey.QuestionDto;
import com.react.survey.dtos.survey.SurveyDto;
import com.react.survey.entities.answer.SurveyStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserSurveyDto {
    private int userSurveyId;
    private SurveyDto surveyDto;
    private QuestionDto lastQuestionDto;
    @Enumerated(EnumType.STRING)
    private SurveyStatus status;
}
