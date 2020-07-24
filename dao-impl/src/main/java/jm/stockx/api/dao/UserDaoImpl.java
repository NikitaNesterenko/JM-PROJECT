package jm.stockx.api.dao;

import jm.stockx.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class UserDaoImpl extends AbstractDAO<User, Long> implements UserDAO {

    @Override
    public Optional<User> getByName(String name) {
        User user = (User) entityManager.createNativeQuery("SELECT * FROM users AS u WHERE u.username = :username")
                .setParameter("username", name)
                .getSingleResult();
        return Optional.of(user);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        User user = (User) entityManager.createNativeQuery("SELECT * FROM users AS u WHERE u.email = :email")
                .setParameter("email", email)
                .getSingleResult();
        return Optional.of(user);
    }

    @Override
    public Optional<User> getByAppleId(String appleId) {
        User user = (User) entityManager.createNativeQuery("SELECT * FROM users AS u WHERE u.apple_user_id = :appleId")
                .setParameter("appleId", appleId)
                .getSingleResult();
        return Optional.of(user);
    }
}
