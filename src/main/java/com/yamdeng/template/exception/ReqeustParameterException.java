package com.yamdeng.template.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReqeustParameterException extends RuntimeException {
    
    private List<FieldError> errorObjects;

    public ReqeustParameterException(List<ObjectError> errorObjects) {
        super("Bad Request Parameter");
        this.errorObjects = errorObjects.stream().map(errorObject -> {
            return (FieldError) errorObject;
        }).collect(Collectors.toList());
    }
}
