package com.yamdeng.template.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

import lombok.Data;

@Data
public class ExceptionDto {

    private final String message;

    private final String stackTraceMessage;

    private List<ValidationErrorDto> validationErrors;

    @Data
    private static class ValidationErrorDto {

        private final String filed;
        private final Object rejectValue;
        private final String message;
        private final String messageCode;

        private ValidationErrorDto(String filed, String message, Object rejectValue, String messageCode) {
            this.filed = filed;
            this.message = message;
            this.rejectValue = rejectValue;
            this.messageCode = messageCode;
        }

        @Override
        public String toString() {
            return "ValidationErrorDto [filed=" + filed + ", message=" + message + ", messageCode=" + messageCode
                    + ", rejectValue=" + rejectValue + "]";
        }

    }

    public ExceptionDto(String message, String stackTraceMessage) {
        this.message = message;
        this.stackTraceMessage = stackTraceMessage;
    }

    public ExceptionDto(String message, String stackTraceMessage, List<FieldError> errorObjects) {
        this.message = message;
        this.stackTraceMessage = stackTraceMessage;
        List<ValidationErrorDto> validationErrors = new ArrayList<>();
        errorObjects.forEach(errorObject -> {
            ValidationErrorDto validationErrorDto = 
                new ValidationErrorDto(
                    errorObject.getField(), errorObject.getDefaultMessage(), errorObject.getRejectedValue(), errorObject.getCode());
            validationErrors.add(validationErrorDto);
        });
        this.validationErrors = validationErrors;
    }
    
}
