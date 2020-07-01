package jm.dao;

import jm.User;
import jm.api.dao.UserDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class UserDaoImpl extends AbstractDAO<User> implements UserDAO {

    @Override
    public Optional<User> getUserByUsername(String name) {
        try {
            User user = (User) entityManager.createQuery("FROM User u where u.username = :username")
                    .setParameter("username", name)
                    .getSingleResult();
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
