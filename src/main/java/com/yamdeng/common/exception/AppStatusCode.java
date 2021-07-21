package com.yamdeng.common.exception;

public enum AppStatusCode {
    
    CLIENT_ERROR("client"), SERVER_ERROR("server");

    private final String errorName;

    private AppStatusCode(String errorName) {
        this.errorName = errorName;
    }

    @Override
    public String toString() {
        return "ApplicationStatusCode{" +
            "errorName='" + errorName + '\'' +
            '}';
    }
}
