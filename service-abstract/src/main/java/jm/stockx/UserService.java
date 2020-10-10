package jm.stockx;

import jm.stockx.dto.user.UserDto;
import jm.stockx.dto.user.UserPutDto;
import jm.stockx.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void createUser(User user);

    void deleteUser(Long id);

    void updateUser(User user);

    public void updateUserFromDto(UserPutDto userPutDto);

    UserDto getUserDtoByUserId(Long id);

    UserDto getUserDtoByUserUsername(String username);

    UserDto getUserDtoByUserEmail(String email);

    UserDto getUserDtoByUserAppleId(String appleId);

    void login(String username, String password, Collection<? extends GrantedAuthority> authorities);

    boolean isUserExist(Long id);

    User getUserByUsername(String username);

    User getUserById(Long id);

    User getUserByEmail(String email);

}
