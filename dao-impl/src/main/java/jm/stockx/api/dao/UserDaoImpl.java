package jm.stockx.api.dao;

import jm.stockx.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl extends AbstractDAO<User, Long> implements UserDAO {

    @Override
    public Optional<User> getByName(String name) {
        String query = "" +
                "FROM User AS u " +
                "WHERE u.username = :username";
        User user = entityManager.createQuery(query, User.class)
                .setParameter("username", name)
                .getSingleResult();

        return Optional.of(user);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        String query = "" +
                "FROM User AS u " +
                "WHERE u.email = :email";
        User user = entityManager.createQuery(query, User.class)
                .setParameter("email", email)
                .getSingleResult();

        return Optional.of(user);
    }

    @Override
    public Optional<User> getByAppleId(String appleId) {
        String query = "" +
                "FROM User AS u " +
                "WHERE u.appleUserId = :appleId";
        User user = entityManager.createQuery(query, User.class)
                .setParameter("appleId", appleId)
                .getSingleResult();

        return Optional.of(user);
    }
}
