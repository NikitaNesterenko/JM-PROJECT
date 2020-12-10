package jm.stockx;

public class UserExistsAdviceException extends Exception{
    public UserExistsAdviceException() {super("Пользователь с введенными данными уже существует");}
}
