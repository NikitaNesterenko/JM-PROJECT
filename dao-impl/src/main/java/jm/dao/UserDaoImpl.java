package jm.dao;

import jm.User;
import jm.api.dao.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class UserDaoImpl extends AbstractDAO<User> implements UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public Optional<User> getUserByUsername(String name) {
        try {
            User user = (User) entityManager.createNativeQuery("SELECT * FROM users AS u WHERE u.username = :username")
                    .setParameter("username", name)
                    .getSingleResult();
            logger.debug("UserDaoImpl. Получен пользователь: {}", user);
            return Optional.of(user);
        } catch (Exception e) {
            logger.debug("UserDaoImpl. Не удалось получить пользователя по имени {}", name, e);
            return Optional.empty();
        }
    }
}
