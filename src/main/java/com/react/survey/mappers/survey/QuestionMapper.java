package com.react.survey.mappers.survey;

import com.react.survey.dtos.survey.QuestionDTO;
import com.react.survey.entities.survey.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = OptionMapper.class)
public interface QuestionMapper {

    Question toQuestion(QuestionDTO dto);

    QuestionDTO toQuestionDto(Question question);
}

