package jm;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void createUser(User user);

    void deleteUser(Long id);

    void updateUser(User user);

    User getUserById(Long id);

    User getUserByUserName(String userName);
}
