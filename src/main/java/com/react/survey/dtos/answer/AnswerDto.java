package com.react.survey.dtos.answer;

import com.react.survey.dtos.survey.OptionDto;
import com.react.survey.dtos.survey.QuestionDto;
import lombok.Data;

@Data
public class AnswerDto {
    private QuestionDto questionDto;
    private OptionDto optionDto;
    private String textAreaAnswer;
}
