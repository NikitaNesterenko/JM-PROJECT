package jm.stockx.api.dao;

import jm.stockx.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class UserDaoImpl extends AbstractDAO<User> implements UserDAO {

    @Override
    public Optional<User> getUserByUsername(String name) {
            User user = (User) entityManager.createNativeQuery("SELECT * FROM users AS u WHERE u.username = :username")
                    .setParameter("username", name)
                    .getSingleResult();
            return Optional.of(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
            User user = (User) entityManager.createNativeQuery("SELECT * FROM users AS u WHERE u.email = :email")
                    .setParameter("email", email)
                    .getSingleResult();
            return Optional.of(user);
    }
}
