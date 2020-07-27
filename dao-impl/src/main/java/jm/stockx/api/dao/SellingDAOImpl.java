package jm.stockx.api.dao;

import jm.stockx.entity.SellingInfo;
import jm.stockx.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SellingDAOImpl extends AbstractDAO<SellingInfo, Long> implements SellingDAO {
    @Override
    public List<User> getTop20SellingUsers(){
        String sql = "select i.* from selling_info as si left join users as i on si.user_id=i.id " +
                " group by si.user_id order by count(si.item_id) desc";
        return entityManager.createNativeQuery(sql, User.class)
                .setMaxResults(20)
                .getResultList();
    }
}
