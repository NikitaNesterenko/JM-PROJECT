package jm.stockx;


import jm.stockx.dto.UserRegistrationDto;
import jm.stockx.util.Response;

import javax.validation.Valid;

public interface UserRegistrationService {

    Response<?> registrationUser (@Valid UserRegistrationDto user);

}
