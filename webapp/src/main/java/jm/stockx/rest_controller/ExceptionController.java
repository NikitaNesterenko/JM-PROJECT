package jm.stockx.rest_controller;

import jm.stockx.*;
import jm.stockx.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "jm.stockx")
public class ExceptionController extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);


    @ExceptionHandler(AuthorizationException.class)
    public Response<String> authorizationNotFoundException(AuthorizationException e) {
        logger.error(e.getMessage());
        return Response.error(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(BidException.class)
    public Response<String> bidPlaceException(BidException e) {
        logger.error(e.getMessage());
        return Response.error(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public Response<String> userNotFoundException(UserNotFoundException e) {
        logger.error((e.getMessage()));
        return Response.error(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(RecoveryException.class)
    public Response<String> recoveryException(RecoveryException e) {
        logger.error(e.getMessage());
        return Response.error(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(UserExistsException.class)
    public Response<String> userExistsException(UserExistsException e) {
        logger.error(e.getMessage());
        return Response.error(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}