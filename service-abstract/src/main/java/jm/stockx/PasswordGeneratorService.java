package jm.stockx;

import javax.validation.constraints.Positive;

public interface PasswordGeneratorService {

    String generatePassword(@Positive Integer count);

}
