package com.yamdeng.template.exception;

public class ApplicationException extends RuntimeException {

    public ApplicationException() {
        super();
    }

    public ApplicationException(Throwable e) {
        super(e);
    }

    public ApplicationException(String errorMessage) {
        super(errorMessage);
    }

    public ApplicationException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }

}
