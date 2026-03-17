package com.react.survey.mappers.survey;

import com.react.survey.dtos.survey.QuestionDto;
import com.react.survey.entities.survey.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = OptionMapper.class)
public interface QuestionMapper {

    Question toQuestion(QuestionDto dto);

    QuestionDto toQuestionDto(Question question);
}

