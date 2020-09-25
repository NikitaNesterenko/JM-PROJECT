package jm.stockx.api.dao;

import jm.stockx.dto.UserDto;
import jm.stockx.entity.User;

public interface UserDAO extends GenericDao<User, Long> {
    UserDto getUserDtoByName(String name);

    UserDto getUserDtoByEmail(String name);

    UserDto getUserDtoByAppleId(String appleId);

    UserDto getUserDtoById(Long id);

    User getUserByName(String name);

    User getUserById(Long id);

}
