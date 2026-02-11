package com.react.survey.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class QuestionDTO {
    private String text;
    private String type;
    private boolean required;
    private List<OptionDTO> options;
}
