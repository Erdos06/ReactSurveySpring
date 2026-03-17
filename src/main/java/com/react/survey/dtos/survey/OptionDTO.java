package com.react.survey.dtos.survey;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OptionDTO {
    private int optionId;
    private String optionText;
}
