package jm.stockx;


import jm.stockx.dto.UserRegistrationDto;

import javax.validation.Valid;

public interface UserRegistrationService {

    void registrationUser(@Valid UserRegistrationDto user) throws UserExistsException, UserNotFoundException;

}
