package jm.stockx;

import jm.stockx.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void createUser(User user);

    void deleteUser(Long id);

    void updateUser(User user);

    User getUserById(Long id);

    User getUserByUserName(String userName);

    User getUserByEmail(String email);

    User getUserByAppleUserId(String appleId);

    void login(String username, String password, Collection<? extends GrantedAuthority> authorities);

    boolean isUserExist(Long id);

    List<String> getAllUsersMail();
}
