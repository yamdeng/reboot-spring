package com.yamdeng.common.exception;

public class ApplicationException extends RuntimeException {

    private AppStatusCode statusCode;

    public ApplicationException(String message) {
        super(message);
        this.statusCode = AppStatusCode.SERVER_ERROR;
    }

    public ApplicationException(Throwable throwable) {
        super(throwable);
        this.statusCode = AppStatusCode.SERVER_ERROR;
    }

    public ApplicationException(AppStatusCode appStatusCode, String message) {
        super(message);
        this.statusCode = appStatusCode;
    }

    public ApplicationException(AppStatusCode appStatusCode, Throwable throwable) {
        super(throwable);
        this.statusCode = appStatusCode;
    }

    public ApplicationException(AppStatusCode appStatusCode, String message, Throwable throwable) {
        super(message, throwable);
        this.statusCode = appStatusCode;
    }

    @Override
    public String toString() {
        return "ApplicationException{" +
            "statusCode=" + statusCode +
            ", message=" + this.getMessage() +
            "}";
    }
}
