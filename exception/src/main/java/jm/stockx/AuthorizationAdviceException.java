package jm.stockx;

public class AuthorizationAdviceException extends Exception{
    public AuthorizationAdviceException(){
        super("Логин или пароль введны не верно");
    }
}
