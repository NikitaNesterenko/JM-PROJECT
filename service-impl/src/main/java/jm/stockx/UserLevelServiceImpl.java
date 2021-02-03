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

    @Autowired
    public UserLevelServiceImpl(SellingInfoDAO sellingInfoDAO, ItemInfoDAO itemInfoDAO, UserLevelDAO userLevelDAO, UserDAO userDAO) {
        this.sellingInfoDAO = sellingInfoDAO;
        this.itemInfoDAO = itemInfoDAO;
        this.userLevelDAO = userLevelDAO;
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
        int newLevel;
        if ((result = levelProgress / 10) > 100) {
            newLevel = 5;
        } else {
            if (result > 60) {
                newLevel = 4;
            }   else {
                if (result > 30) {
                    newLevel = 3;
                } else {
                    if (result > 10) {
                        newLevel = 2;
                    } else newLevel = 1;
                }
            }
        }


        int levelBalance;
        if ((result = levelProgress / 10) > 100) {
            levelBalance = levelProgress - 1000;
        } else {
            if (result > 60) {
                levelBalance = levelProgress - 600;
            } else {
                if (result > 30) {
                    levelBalance = levelProgress - 300;
                } else {
                    if (result > 10) {
                        levelBalance = levelProgress - 100;
                    } else {
                        levelBalance = levelProgress;
                    }
                }
            }
        }

        userLevel.setLevel(newLevel);
        userLevel.setLevelProgress(levelBalance);

        userLevelDAO.update(userLevel);
    }

}
