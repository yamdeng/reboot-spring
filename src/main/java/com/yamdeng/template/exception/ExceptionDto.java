package com.yamdeng.template.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ExceptionDto {

    private final String message;

    private final String stackTraceMessage;
    
}
