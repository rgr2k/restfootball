package de.planerio.developertest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LeagueNotFoundException extends RuntimeException{

    public LeagueNotFoundException(String message) {
        super(message);
    }

    public LeagueNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LeagueNotFoundException(Throwable cause) {
        super(cause);
    }

    public LeagueNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
