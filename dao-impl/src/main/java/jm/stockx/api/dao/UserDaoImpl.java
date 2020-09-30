package jm.stockx.api.dao;

import jm.stockx.dto.UserDto;
import jm.stockx.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDAO<User, Long> implements UserDAO {

    @Override
    public UserDto getUserDtoByName(String name) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.UserDto(" +
                "u.firstName," +
                "u.lastName," +
                "u.email," +
                "u.username," +
                "u.password," +
                "u.sellerLevel," +
                "u.vacationMode," +
                "u.localeTag" +
                ")" +
                "FROM User AS u " +
                "WHERE u.firstName =: name", UserDto.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public UserDto getUserDtoByEmail(String email) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.UserDto(" +
                "u.firstName," +
                "u.lastName," +
                "u.email," +
                "u.username," +
                "u.password," +
                "u.sellerLevel," +
                "u.vacationMode," +
                "u.localeTag" +
                ")" +
                "FROM User AS u " +
                "WHERE u.email =: email", UserDto.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public UserDto getUserDtoByAppleId(String appleId) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.UserDto(" +
                "u.firstName," +
                "u.lastName," +
                "u.email," +
                "u.username," +
                "u.password," +
                "u.sellerLevel," +
                "u.vacationMode," +
                "u.localeTag" +
                ")" +
                "FROM User AS u " +
                "WHERE u.appleUserId =: appleId", UserDto.class)
                .setParameter("appleId", appleId)
                .getSingleResult();
    }

    @Override
    public UserDto getUserDtoById(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.UserDto(" +
                "u.firstName," +
                "u.lastName," +
                "u.email," +
                "u.username," +
                "u.password," +
                "u.sellerLevel," +
                "u.vacationMode," +
                "u.localeTag" +
                ")" +
                "FROM User AS u " +
                "WHERE u.id =: id", UserDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User getUserByName(String name) {
        return entityManager.createQuery("" +
                "FROM User AS u WHERE u.name = : name", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.createQuery("" +
                "FROM User AS u WHERE u.id = : name", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            return entityManager.createQuery("" +
                    "FROM User AS u WHERE u.id = : email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            e.getMessage();
            return null;
        }

    }
}
