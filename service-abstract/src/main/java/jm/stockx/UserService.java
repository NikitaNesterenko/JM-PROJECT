package jm.stockx;

import jm.stockx.dto.UserDto;
import jm.stockx.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

public interface UserService {

    Set<User> getAllUsers();

    void createUser(User user);

    void deleteUser(Long id);

    void updateUser(User user);

    UserDto getUserDtoById(Long id);

    UserDto getUserDtoByUserName(String userName);

    UserDto getUserDtoByEmail(String email);

    UserDto getUserByAppleUserId(String appleId);

    void login(String username, String password, Collection<? extends GrantedAuthority> authorities);

    boolean isUserExist(Long id);

    User getUserByName(String email);

    User getUserById(Long id);

    User getUserByEmail(String email);

}
