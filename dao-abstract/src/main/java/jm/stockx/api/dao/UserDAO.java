package jm.stockx.api.dao;

import jm.stockx.dto.UserDto;
import jm.stockx.entity.User;

import java.util.Optional;

public interface UserDAO extends GenericDao<User, Long> {
    Optional<User> getByName(String name);
    Optional<User> getByEmail(String name);
    Optional<User> getByAppleId(String appleId);
    UserDto getUserDtoById(Long id);
}
