package com.react.survey.dtos.answer;

import com.react.survey.dtos.survey.OptionDto;
import com.react.survey.dtos.survey.QuestionDto;
import lombok.Data;

import java.util.List;

@Data
public class AnswerDto {
    private QuestionDto questionDto;
    private List<OptionDto> optionDtos;
    private String textAreaAnswer;
}
