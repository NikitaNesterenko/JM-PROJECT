package jm.stockx.api.dao;

import jm.stockx.dto.user.UserDto;
import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.Item;
import jm.stockx.entity.User;
import jm.stockx.enums.ItemCategory;
import org.springframework.stereotype.Repository;

import javax.mail.search.SearchTerm;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Repository
public class UserDaoImpl extends AbstractDAO<User, Long> implements UserDAO {


    public HashMap<ItemCategory, Double> getPurchaseStatisticsByUserId(Long id) {
        HashMap<ItemCategory, Double> hashMap = new HashMap<>();

        int all = 0;
        Set<BuyingInfo> buyingInfoSet = getUserById(id).getBuyingInfo();

        for (BuyingInfo el : buyingInfoSet
        ) {
            for (Item item : el.getBoughtItems()
            ) {
                all++;
                hashMap.merge(item.getItemCategory(), 1.0, Double::sum);
            }
        }

        HashMap<ItemCategory, Double> result = new HashMap<>();
        for (BuyingInfo el : buyingInfoSet
        ) {
            for (Item item : el.getBoughtItems()
            ) {
                all++;
                hashMap.put(item.getItemCategory(), all / hashMap.get(item.getItemCategory()));
            }
        }

        return result;
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
