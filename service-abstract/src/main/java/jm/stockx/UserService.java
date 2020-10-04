package jm.stockx;

import jm.stockx.dto.user.UserDto;
import jm.stockx.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void createUser(User user);

    void deleteUser(Long id);

    void updateUser(User user);

    UserDto getUserDtoByUserId(Long id);

    UserDto getUserDtoByUserName(String userName);

    UserDto getUserDtoByUserEmail(String email);

    UserDto getUserDtoByUserAppleId(String appleId);

    void login(String username, String password, Collection<? extends GrantedAuthority> authorities);

    boolean isUserExist(Long id);

    User getUserByName(String email);

    User getUserById(Long id);

    User getUserByEmail(String email);

}
