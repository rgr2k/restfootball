package de.planerio.developertest.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExpectionResponse> handleAllExceptions(Exception exception, WebRequest webRequest){
      ExpectionResponse expectionResponse = new ExpectionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
      return new ResponseEntity<>(expectionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CountryNotFoundException.class)
    public final ResponseEntity<ExpectionResponse> handleCountryNotFoundException(Exception exception, WebRequest webRequest){
        ExpectionResponse expectionResponse = new ExpectionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(expectionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LeagueNotFoundException.class)
    public final ResponseEntity<ExpectionResponse> handleLeagueNotFoundException(Exception exception, WebRequest webRequest){
        ExpectionResponse expectionResponse = new ExpectionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(expectionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public final ResponseEntity<ExpectionResponse> handlePlayerNotFoundException(Exception exception, WebRequest webRequest){
        ExpectionResponse expectionResponse = new ExpectionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(expectionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TeamNotFoundException.class)
    public final ResponseEntity<ExpectionResponse> handleTeamNotFoundException(Exception exception, WebRequest webRequest){
        ExpectionResponse expectionResponse = new ExpectionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(expectionResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExpectionResponse expectionResponse = new ExpectionResponse(new Date(), "Validation failed", ex.getBindingResult().toString());
        return new ResponseEntity<>(expectionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceExistsException.class)
    public final ResponseEntity<ExpectionResponse> handleResourceExistsException(Exception exception, WebRequest webRequest){
        ExpectionResponse expectionResponse = new ExpectionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(expectionResponse, HttpStatus.CONFLICT);
    }
}
