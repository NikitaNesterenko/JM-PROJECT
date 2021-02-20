package jm.stockx;

import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.User;

public interface LetterService {

    String createBuyingLetter (User user, BuyingInfo buyingInfo);
}
