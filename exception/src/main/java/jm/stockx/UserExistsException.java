package jm.stockx;

public class UserExistsException extends Exception{
    public UserExistsException() { super("Пользователь с введенными данными уже существует"); }
}
