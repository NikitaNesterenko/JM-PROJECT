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

@Service
public class UserLevelServiceImpl implements UserLevelService{

    private SellingInfoDAO sellingInfoDAO;
    private ItemInfoDAO itemInfoDAO;
    private UserLevelDAO userLevelDAO;
    private final UserDAO userDAO;


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
        Long levelProgress = (sellingInfoDAO.getCountOfUserSalesByUserId(userId) * 20L)
                + (itemInfoDAO.getCountOfUserBuyingByUserId(userId) * 10L);
        userLevel.setLevelProgress(levelProgress);

        if (levelProgress > 100 & levelProgress <= 200) {
            userLevel.setLevelName(LevelName.SECOND_LEVEL);
        }
        else if (levelProgress > 200 & levelProgress <= 300) {
            userLevel.setLevelName(LevelName.THIRD_LEVEL);
        }
        else if (levelProgress > 300 & levelProgress <= 400) {
            userLevel.setLevelName(LevelName.FOURTH_LEVEL);
        }
        else if (levelProgress > 400) {
            userLevel.setLevelName(LevelName.FIFTH_LEVEL);
        }

        userLevelDAO.update(userLevel);
    }
}
