package jm.stockx;

import jm.stockx.api.dao.UserDAO;
import jm.stockx.api.dao.UserLocaliseInfoDAO;
import jm.stockx.entity.UserLocaliseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserLocaliseInfoServiceImpl implements UserLocaliseInfoService{

    private UserLocaliseInfoDAO userLocaliseInfoDAO;
    private final UserDAO userDAO;

    @Autowired
    public UserLocaliseInfoServiceImpl(UserLocaliseInfoDAO userLocaliseInfoDAO, UserDAO userDAO) {
        this.userLocaliseInfoDAO = userLocaliseInfoDAO;
        this.userDAO = userDAO;
    }

    @Override
    public List<UserLocaliseInfo> getAll() {
        return userLocaliseInfoDAO.getAll();
    }

    @Override
    public void create(UserLocaliseInfo userLocaliseInfo) {
        userLocaliseInfoDAO.add(userLocaliseInfo);
    }

    @Override
    public void update(UserLocaliseInfo userLocaliseInfo) {
        userLocaliseInfoDAO.update(userLocaliseInfo);
    }

    @Override
    public String getMoneyTagByUserId(Long id) {
        return userLocaliseInfoDAO.getMoneyTagByUserId(id);
    }
}
