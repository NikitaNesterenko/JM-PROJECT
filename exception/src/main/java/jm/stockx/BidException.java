package jm.stockx;

public class BidException extends Exception{
    public BidException() { super("Возникла ошибка при размещении или обновлении ставки"); }
}
