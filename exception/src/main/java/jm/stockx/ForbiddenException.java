package jm.stockx;

public class ForbiddenException extends Exception{
    public ForbiddenException() { super("Не достаточно прав"); }
}
