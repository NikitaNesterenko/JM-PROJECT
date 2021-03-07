package jm.stockx;

import jm.stockx.api.dao.UserDAO;
import jm.stockx.api.dao.UserLocaliseInfoDAO;
import jm.stockx.dto.userLocaliseInfo.UserLocaliseInfoDto;
import jm.stockx.dto.userLocaliseInfo.UserLocaliseInfoPostDto;
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
    public void deleteByUserName(String userName) {
        userLocaliseInfoDAO.deleteByUserName(userName);
    }

    @Override
    public UserLocaliseInfoDto getUserLocaliseInfoDtoByUserName(String userName) {
        return userLocaliseInfoDAO.getUserLocaliseInfoDtoByUserName(userName);
    }

    @Override
    public void placeUserLocaliseInfo(UserLocaliseInfoPostDto userLocaliseInfoPostDto) {
        UserLocaliseInfo userLocaliseInfo = UserLocaliseInfo.builder()
                .userCountry(userLocaliseInfoPostDto.getUserCountry())
                .userCurrency(userLocaliseInfoPostDto.getUserCurrency())
                .userLanguage(userLocaliseInfoPostDto.getUserLanguage())
                .user(userDAO.getUserById(userLocaliseInfoPostDto.getUserId()))
                .build();
        userLocaliseInfoDAO.add(userLocaliseInfo);
    }
}
