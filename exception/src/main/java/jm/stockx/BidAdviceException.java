package jm.stockx;

public class BidAdviceException extends Exception{
    public BidAdviceException() { super("Возникла ошибка при размещении или обновлении ставки");}
}
