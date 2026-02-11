package com.react.survey.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class SurveyDTO {
    private String title;
    private String author;
    private String description;
    private List<QuestionDTO> questions;
}
