package jm.stockx.rest_controller;

import jm.stockx.AuthorizationAdviceException;
import jm.stockx.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@RestController
@RestControllerAdvice (basePackages = "jm.stockx")
public class ExceptionController extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
    @ExceptionHandler(AuthorizationAdviceException.class)
    public Response<?> AuthorizationNotFoundException(AuthorizationAdviceException e){
        logger.error(e.getMessage());
        return Response.error(HttpStatus.NOT_FOUND, "Логин или пароль введны не верно");
    }
}
