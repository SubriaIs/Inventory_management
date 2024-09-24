package com.subria.fi.exceptions;

import lombok.Getter;

@Getter
public class IMServiceException extends RuntimeException {
    private int httpRequestCode;
    private String error;

    public IMServiceException(String message) {
        this(message, 500);

    }

    public IMServiceException(String message, int httpStatusCode) {

        this(message,httpStatusCode,"Internal Error");
    }

    public IMServiceException(String message, int httpStatusCode, String errorMessage) {
        super(message);
        httpRequestCode = httpStatusCode;
        error = errorMessage;
    }
}
