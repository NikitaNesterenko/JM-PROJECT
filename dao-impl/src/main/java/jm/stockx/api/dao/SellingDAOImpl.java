package jm.stockx.api.dao;

import jm.stockx.entity.SellingInfo;
import jm.stockx.entity.User;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class SellingDAOImpl extends AbstractDAO<SellingInfo, Long> implements SellingDAO {
    @Override
    public List<User> getTopSellingUsers(){
        String sql = "select i.* from selling_info as si left join users as i on si.user_id=i.id " +
                " group by si.user_id order by count(si.item_id) desc";
        return entityManager.createNativeQuery(sql, User.class)
                .setMaxResults(20)
                .getResultList();
    }

    public Integer getCountOfSellByUserId(Long id){
        BigInteger bigInteger = (BigInteger) entityManager.createNativeQuery("select count(si.item_id) from selling_info as si left join users as u " +
                "on si.user_id = u.id " +
                "where u.id = :id")
                .setParameter("id", id)
                .getSingleResult();
        return bigInteger.intValue();
    }
}
