package jm.stockx.api.dao;

import jm.stockx.dto.user.UserDto;
import jm.stockx.dto.user.UserPurchaseDto;
import jm.stockx.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends AbstractDAO<User, Long> implements UserDAO {

    @Override
    public List<UserPurchaseDto> getPurchaseStatisticsByUserId(Long id) {

        return entityManager.createNativeQuery(
                "SELECT i.item_category, count(i.item_category) " +
                        "FROM userdb.public.items AS i " +
                        "WHERE id IN " +
                        "(SELECT bi.item_id FROM userdb.public.buying_item AS bi " +
                        "WHERE buying_id IN " +
                        "(SELECT ub.buying_id FROM userdb.public.user_buying AS ub " +
                        "WHERE user_id = :userid)" +
                        ")" +
                        "group by i.item_category ", UserPurchaseDto.class)
                .setParameter("userid", id)
                .getResultList();
    }

    @Override
    public UserDto getUserDtoByUserUsername(String username) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.user.UserDto(" +
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
                "WHERE u.username =: username", UserDto.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public UserDto getUserDtoByUserEmail(String email) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.user.UserDto(" +
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
    public UserDto getUserDtoByUserAppleId(String appleId) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.user.UserDto(" +
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
    public UserDto getUserDtoByUserId(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.user.UserDto(" +
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
    public User getUserByUsername(String username) {
        return entityManager.createQuery("" +
                "FROM User AS u WHERE u.username =: username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.createQuery("" +
                "FROM User AS u WHERE u.id =: id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            return entityManager.createQuery("" +
                "FROM User AS u WHERE u.email =: email", User.class)
                .setParameter("email", email)
                .getSingleResult();
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
