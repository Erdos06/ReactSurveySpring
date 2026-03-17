package com.react.survey.mappers.survey;

import com.react.survey.dtos.survey.OptionDTO;
import com.react.survey.entities.survey.Option;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OptionMapper {

    Option toOption(OptionDTO dto);

    OptionDTO toOptionDto(Option option);
}

