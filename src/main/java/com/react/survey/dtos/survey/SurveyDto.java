package com.react.survey.dtos.survey;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class SurveyDto {
    private int surveyId;
    private String title;
    private String author;
    private String description;
    private List<QuestionDto> questions;
}
