package jm;

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

    void login(String username, String password, Collection<? extends GrantedAuthority> authorities);
}
