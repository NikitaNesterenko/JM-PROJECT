package jm.stockx.api.dao;

import jm.stockx.dto.item.ItemPurchaseDto;
import jm.stockx.dto.user.UserDto;
import jm.stockx.dto.user.UserEmailDto;
import jm.stockx.dto.user.UserPutDto;
import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.Item;
import jm.stockx.entity.ItemInfo;
import jm.stockx.entity.User;
import jm.stockx.enums.ItemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl extends AbstractDAO<User, Long> implements UserDAO {

    @Autowired
    ItemInfoDAO itemInfoDAO;

    @Override
    public List<ItemPurchaseDto> getPurchaseStatisticsByUserId(Long id) {
        List<Tuple> list = entityManager.createNativeQuery(
                "SELECT i.item_category AS COfI, " +
                        "count(i.item_category) AS Count " +
                        "FROM userdb.public.item_info AS i " +
                        "JOIN buying_item b " +
                        "    on i.id = b.item_id " +
                        "JOIN user_buying u  " +
                        "    on b.buying_id = u.buying_id " +
                        "WHERE user_id = :userid " +
                        "GROUP BY i.item_category " +
                        "ORDER BY i.item_category" +
                        "", Tuple.class)
                .setParameter("userid", id)
                .getResultList();

        List<ItemPurchaseDto> itemPurchaseDtoList = new ArrayList<>();
        for (Tuple el : list
        ) {
            itemPurchaseDtoList.add(new ItemPurchaseDto(
                    el.get("COfI", String.class),
                    el.get("Count", BigInteger.class).intValue()
            ));
        }
        return itemPurchaseDtoList;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        Long existingValue = entityManager.createQuery("" +
                "SELECT COUNT(u.email) " +
                "FROM User u " +
                "WHERE u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();

        return existingValue > 0;
    }

    @Override
    public List<UserEmailDto> getUserEmailDtoByItemCategory(ItemCategory itemCategory) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.user.UserEmailDto(" +
                "u.email" +
                ")" +
                "FROM User AS u " +
                "JOIN u.buyingInfo b " +
                "JOIN b.boughtItemsInfo i " +
                "WHERE i.itemCategory = :itemCategory", UserEmailDto.class)
                .setParameter("itemCategory", itemCategory)
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

    public void updateUserFromDto(UserPutDto userPutDto){
        entityManager.createQuery("" +
                "UPDATE User user SET" +
                " user.firstName = :firstNameFromDto," +
                " user.lastName = :lastNameFromDto " +
                "WHERE user.id = :idFromDto")
                .setParameter("idFromDto",userPutDto.getId())
                .setParameter("firstNameFromDto",userPutDto.getFirstName())
                .setParameter("lastNameFromDto",userPutDto.getLastName())
                .executeUpdate();
    }
}
