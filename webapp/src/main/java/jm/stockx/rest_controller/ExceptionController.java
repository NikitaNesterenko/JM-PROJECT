package jm.stockx.rest_controller;

import jm.stockx.AuthorizationException;
import jm.stockx.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice (basePackages = "jm.stockx")
public class ExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
    @ExceptionHandler(AuthorizationException.class)
    public Response<?> AuthorizationNotFoundException(AuthorizationException e){
        logger.error(e.getMessage());
        return Response.error(HttpStatus.NOT_FOUND, "Логин или пароль введны не верно");
    }
}
