package com.react.survey.mappers.survey;

import com.react.survey.dtos.survey.OptionDto;
import com.react.survey.entities.survey.Option;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OptionMapper {

    Option toOption(OptionDto dto);

    OptionDto toOptionDto(Option option);
}

