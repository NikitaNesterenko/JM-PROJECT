package jm.stockx;

import jm.stockx.api.dao.ItemInfoDAO;
import jm.stockx.api.dao.SellingInfoDAO;
import jm.stockx.api.dao.UserDAO;
import jm.stockx.api.dao.UserLevelDAO;
import jm.stockx.entity.UserLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserLevelServiceImpl implements UserLevelService{

    private SellingInfoDAO sellingInfoDAO;
    private ItemInfoDAO itemInfoDAO;
    private UserLevelDAO userLevelDAO;
    private UserDAO userDAO;


    @Autowired
    public UserLevelServiceImpl(SellingInfoDAO sellingInfoDAO, ItemInfoDAO itemInfoDAO, UserLevelDAO userLevelDAO, UserDAO userDAO) {
        this.sellingInfoDAO = sellingInfoDAO;
        this.itemInfoDAO = itemInfoDAO;
        this.userLevelDAO = userLevelDAO;
        this.userDAO = userDAO;
    }

    public UserLevelServiceImpl() {

    }

    @Transactional
    @Override
    public void updateUserLevelByUserId(Long userId) {
        int newLevel = 0;
        int levelBalance = 0;
        UserLevel userLevel = userDAO.getUserById(userId).getUserLevel();

        int levelProgress = (sellingInfoDAO.getCountOfUserSalesByUserId(userId) * 20)
                + (itemInfoDAO.getCountOfUserBuyingByUserId(userId) * 10);

        for (int levelTop = 100; levelProgress > 0; newLevel++) {
            if (newLevel >= 5) { break; }
            levelBalance = levelProgress;
            levelProgress = levelProgress - levelTop;
            levelTop = levelTop + 100;
        }

        userLevel.setLevel(newLevel);
        userLevel.setLevelProgress(levelBalance);

        userLevelDAO.update(userLevel);
    }
}
