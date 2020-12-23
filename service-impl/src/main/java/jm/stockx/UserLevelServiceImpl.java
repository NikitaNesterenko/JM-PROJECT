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
        int result = 0;

        UserLevel userLevel = userLevelDAO.getUserLevelByUserId(userId);
        int levelProgress = (sellingInfoDAO.getCountOfUserSalesByUserId(userId) * 20)
                + (itemInfoDAO.getCountOfUserBuyingByUserId(userId) * 10);

        int newLevel = (result = levelProgress/10) > 100 ? 5 :
                result > 60 ? 4 : result > 30 ? 3 : result > 10 ? 2 : 1;

        int levelBalance = (result = levelProgress/10) > 100 ? levelProgress - 1000 :
                result > 60 ? levelProgress - 600 : result > 30 ? levelProgress - 300 :
                        result > 10 ? levelProgress - 100 : levelProgress;

        userLevel.setLevel(newLevel);
        userLevel.setLevelProgress(levelBalance);

        userLevelDAO.update(userLevel);
    }

}
