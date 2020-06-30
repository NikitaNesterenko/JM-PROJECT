package jm;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void addUser(User user);

    void deleteUser(Long id);

    void updateUser(User user);
}
