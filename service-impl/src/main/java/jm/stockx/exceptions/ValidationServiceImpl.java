package jm.stockx.exceptions;

import jm.stockx.ValidationService;
import jm.stockx.api.dao.UserDAO;
import jm.stockx.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidationServiceImpl implements ValidationService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean validateUserDB(String email)  {
        UserDto userDto = userDAO.getUserDtoByUserEmail(email);

        return true;
    }
}
