package com.react.survey.mappers.survey;

import com.react.survey.dtos.survey.QuestionDto;
import com.react.survey.entities.survey.Question;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = OptionMapper.class)
public interface QuestionMapper {

    Question toQuestion(QuestionDto dto);

    QuestionDto toQuestionDto(Question question);

    @AfterMapping
    default void linkQuestions(@MappingTarget Question question) {
        if (question.getOptions() != null) {
            question.getOptions().forEach(option -> option.setQuestion(question));
        }
    }
}

