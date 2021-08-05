package com.yamdeng.template.common;

public enum LogMode {

    DEV("dev"), PROD("prod");

    private final String logMode;

    LogMode(String logMode) {
        this.logMode = logMode;
    }

    @Override
    public String toString() {
        return "logMode :" + logMode;
    }
    
}
