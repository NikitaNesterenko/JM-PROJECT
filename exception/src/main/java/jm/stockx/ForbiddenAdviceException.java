package jm.stockx;

public class ForbiddenAdviceException extends Exception{
    public ForbiddenAdviceException() {super("Не достаточно прав");}
}
