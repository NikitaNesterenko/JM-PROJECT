package jm.stockx;

import org.springframework.security.core.AuthenticationException;

public class AuthorizationException extends AuthenticationException {
    public AuthorizationException(){
        super("Логин или пароль введны не верно");
    }
}
