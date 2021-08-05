package com.yamdeng.template.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDto {

    private String code;
    private String lang;
    private String message;
    
}
