package jm.stockx.api.dao;

import jm.stockx.dto.UserDto;
import jm.stockx.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl extends AbstractDAO<User, Long> implements UserDAO {

    @Override
    public UserDto getByName(String name) {
        User user = entityManager.createQuery("" +
                "FROM User AS u " +
                "WHERE u.username = :username", User.class)
                .setParameter("username", name)
                .getSingleResult();

        return new UserDto(user);
    }

    @Override
    public UserDto getByEmail(String email) {
        User user = entityManager.createQuery("" +
                "FROM User AS u " +
                "WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();

        return new UserDto(user);
    }

    @Override
    public UserDto getByAppleId(String appleId) {
        User user = entityManager.createQuery("" +
                "FROM User AS u " +
                "WHERE u.appleUserId = :appleId", User.class)
                .setParameter("appleId", appleId)
                .getSingleResult();

        return new UserDto(user);
    }

    @Override
    public UserDto getUserDtoById(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.UserDto(" +
                "u.id," +
                "u.firstName," +
                "u.lastName," +
                "u.email," +
                "u.username," +
                "u.password," +
                "u.sellerLevel," +
                "u.vacationMode)" +
                "FROM User AS u " +
                "WHERE u.id =: id", UserDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
