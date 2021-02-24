package jm.stockx.rest_controller;

import jm.stockx.*;
import jm.stockx.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice (basePackages = "jm.stockx")
@Slf4j
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    public Response<String> authorizationNotFoundException(AuthorizationException e){
        log.error(e.getMessage());
        return Response.error(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(BidException.class)
    public Response<String> bidPlaceException(BidException e) {
        log.error(e.getMessage());
        return Response.error(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public Response<String> userNotFoundException(UserNotFoundException e) {
        log.error((e.getMessage()));
        return Response.error(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(RecoveryException.class)
    public Response<String> recoveryException(RecoveryException e) {
        log.error(e.getMessage());
        return Response.error(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(UserExistsException.class)
    public Response<String> userExistsException(UserExistsException e) {
        log.error(e.getMessage());
        return Response.error(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}