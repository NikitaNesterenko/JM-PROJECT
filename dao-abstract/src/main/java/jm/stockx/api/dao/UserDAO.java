package jm.stockx.api.dao;

import jm.stockx.dto.UserDto;
import jm.stockx.entity.User;

import java.util.Optional;

public interface UserDAO extends GenericDao<User, Long> {
    UserDto getByName(String name);
    UserDto getByEmail(String name);
    UserDto getByAppleId(String appleId);
    UserDto getUserDtoById(Long id);
}
