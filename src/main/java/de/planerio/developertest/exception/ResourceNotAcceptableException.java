package de.planerio.developertest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceNotAcceptableException extends RuntimeException{

    public ResourceNotAcceptableException(String message) {
        super(message);
    }

    public ResourceNotAcceptableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotAcceptableException(Throwable cause) {
        super(cause);
    }

    public ResourceNotAcceptableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
