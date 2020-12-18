package jm.stockx;

import jm.stockx.api.dao.ItemInfoDAO;
import jm.stockx.api.dao.SellingInfoDAO;
import jm.stockx.api.dao.UserDAO;
import jm.stockx.api.dao.UserLevelDAO;
import jm.stockx.entity.UserLevel;
import jm.stockx.enums.LevelName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static jm.stockx.enums.LevelName.*;

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

    @Transactional
    @Override
    public void updateUserLevelByUserId(Long userId) {
        UserLevel userLevel = userDAO.getUserById(userId).getUserLevel();
        int levelProgress = (sellingInfoDAO.getCountOfUserSalesByUserId(userId) * 20)
                + (itemInfoDAO.getCountOfUserBuyingByUserId(userId) * 10);

        switch (userLevel.getLevelName()) {
            case FIRST_LEVEL:
                if (levelProgress > 100) {
                    userLevel.setLevelName(SECOND_LEVEL);
                    userLevel.setLevelProgress(levelProgress-100);
                }
                else {
                    userLevel.setLevelProgress(levelProgress);
                    break;
                }
            case SECOND_LEVEL:
                if (levelProgress > 300) {
                    userLevel.setLevelName(THIRD_LEVEL);
                    userLevel.setLevelProgress(levelProgress-300);
                }
                else {
                    userLevel.setLevelProgress(levelProgress-100);
                    break;
                }
            case THIRD_LEVEL:
                if (levelProgress > 600) {
                    userLevel.setLevelName(FOURTH_LEVEL);
                    userLevel.setLevelProgress(levelProgress-600);
                }
                else {
                    userLevel.setLevelProgress(levelProgress-300);
                    break;
                }
            case FOURTH_LEVEL:
                if (levelProgress > 1000) {
                    userLevel.setLevelProgress(levelProgress - 1000);
                    userLevel.setLevelName(FIFTH_LEVEL);
                } else {
                    userLevel.setLevelProgress(levelProgress-600);
                    break;
                }
        }

        userLevelDAO.update(userLevel);
    }

}
