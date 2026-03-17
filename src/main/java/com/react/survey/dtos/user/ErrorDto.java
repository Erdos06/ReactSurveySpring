package com.react.survey.dtos.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorDto {
    private String message;

    public ErrorDto(String unauthorizedPath) {
        message = unauthorizedPath;
    }
}
