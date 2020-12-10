package jm.stockx.rest_controller;

import jm.stockx.*;
import jm.stockx.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice (basePackages = "jm.stockx")
public class ExceptionController extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);


    @ExceptionHandler(AuthorizationAdviceException.class)
    public Response<?> authorizationNotFoundException(AuthorizationAdviceException e){
        logger.error(e.getMessage());
        return Response.error(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(BidAdviceException.class)
    public Response<?> bidPlaceException(BidAdviceException e) {
        logger.error(e.getMessage());
        return Response.error(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(UserNotFoundAdviceException.class)
    public Response<?> userNotFoundException(UserNotFoundAdviceException e) {
        logger.error((e.getMessage()));
        return Response.error(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(RecoveryAdviceException.class)
    public Response<?> recoveryException(RecoveryAdviceException e) {
        logger.error(e.getMessage());
        return Response.error(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(UserExistsAdviceException.class)
    public Response<?> userExistsException(UserExistsAdviceException e) {
        logger.error(e.getMessage());
        return Response.error(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
