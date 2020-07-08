package jm.api.dao;

import jm.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends CrudDao<User>{

    /*List<User> getAll();

    User getById(Long id);

    void addUser(User user);

    void deleteById(Long id);

    User merge(User user);*/

    Optional<User> getUserByUsername(String name);

}
