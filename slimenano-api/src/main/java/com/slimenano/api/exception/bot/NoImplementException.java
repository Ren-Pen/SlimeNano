package com.slimenano.api.exception.bot;

public class NoImplementException extends RuntimeException{

    public NoImplementException() {
    }

    public NoImplementException(String message) {
        super(message);
    }

    public NoImplementException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoImplementException(Throwable cause) {
        super(cause);
    }

    public NoImplementException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
