package jm.stockx;

public class AuthorizationException extends Exception{
    public AuthorizationException(){
        super("Логин или пароль введны не верно");
    }
}
