package jm.stockx;

import jm.stockx.api.dao.UserLocaliseInfoDAO;
import jm.stockx.dto.userLocaliseInfo.UserLocaliseInfoDto;
import jm.stockx.entity.UserLocaliseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserLocaliseInfoServiceImpl implements UserLocaliseInfoService{

    private UserLocaliseInfoDAO userLocaliseInfoDAO;

    @Autowired
    public UserLocaliseInfoServiceImpl(UserLocaliseInfoDAO userLocaliseInfoDAO) {
        this.userLocaliseInfoDAO = userLocaliseInfoDAO;
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
    public void deleteByUserName(String userName) {
        userLocaliseInfoDAO.deleteByUserName(userName);
    }

    @Override
    public UserLocaliseInfoDto getUserLocaliseInfoDtoByUserName(String userName) {
        return userLocaliseInfoDAO.getUserLocaliseInfoDtoByUserName(userName);
    }
}
