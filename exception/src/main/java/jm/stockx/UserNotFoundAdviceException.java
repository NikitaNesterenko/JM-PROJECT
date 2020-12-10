package jm.stockx;

public class UserNotFoundAdviceException extends Exception{
    public UserNotFoundAdviceException() {super("Пользователь не найден");}
}
