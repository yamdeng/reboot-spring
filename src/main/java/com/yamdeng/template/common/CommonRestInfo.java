package com.yamdeng.template.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonRestInfo {

    private String entityName;

    private String uriName;

    private String serviceName;

    private Class<?> entityClass;

    private Class<?> dtoClass;
    
}
