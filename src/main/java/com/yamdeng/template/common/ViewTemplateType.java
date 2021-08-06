package com.yamdeng.template.common;

public enum ViewTemplateType {

    THYMELEAF("thymeleaf"), MUSTACHE("mustache");

    private final String viewTemplateType;

    ViewTemplateType(String viewTemplateType) {
        this.viewTemplateType = viewTemplateType;
    }

    @Override
    public String toString() {
        return "viewTemplateType :" + viewTemplateType;
    }
    
}
