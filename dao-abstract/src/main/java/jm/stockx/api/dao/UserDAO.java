package jm.stockx.api.dao;

import jm.stockx.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO{

    List<User> getAll();

    User getById(Long id);

    void add(User user);

    void deleteById(Long id);

    User merge(User user);

    Optional<User> getUserByUsername(String name);

}
