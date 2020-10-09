package jm.stockx;


import jm.stockx.dto.UserRegistrationDto;
import jm.stockx.util.Response;

public interface UserRegistrationService {

    Response<?> registrationUser (UserRegistrationDto user);

}
