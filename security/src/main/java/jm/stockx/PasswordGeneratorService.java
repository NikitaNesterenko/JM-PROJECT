package jm.stockx;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Positive;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PasswordGeneratorService {

    public String generatePassword(@Positive Integer count) {

        String upperCaseLetters = RandomStringUtils.random(10, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(10, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(10);
        String specialChar = RandomStringUtils.random(5, 35, 39, false, false);
        String totalChars = RandomStringUtils.randomAlphanumeric(5);
        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
                .concat(numbers)
                .concat(specialChar)
                .concat(totalChars);
        List<Character> pwdChars = combinedChars.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());

        Collections.shuffle(pwdChars);

        String password = pwdChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();

        return password.substring(0, count);
    }

}
