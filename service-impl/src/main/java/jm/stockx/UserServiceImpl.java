package jm.stockx;

import jm.stockx.api.dao.UserDAO;
import jm.stockx.dto.user.UserDto;
import jm.stockx.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.add(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public UserDto getUserDtoByUserId(Long id) {
        return userDao.getUserDtoByUserId(id);
    }

    @Override
    public UserDto getUserDtoByUserUsername(String username) {
        return userDao.getUserDtoByUserUsername(username);
    }

    @Override
    public UserDto getUserDtoByUserEmail(String email) {
        return userDao.getUserDtoByUserEmail(email);
    }

    @Override
    public UserDto getUserDtoByUserAppleId(String appleId) {
        return userDao.getUserDtoByUserAppleId(appleId);
    }

    @Override
    public void login(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(username, password, authorities);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authReq);
    }

    @Override
    public boolean isUserExist(Long id) {
        return userDao.doesItExistEntity(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public HashMap<String, Double> getPurchaseStatisticsPercentageByUserId(Long id) {
        List<Tuple> list = userDao.getPurchaseStatisticsByUserId(id);
        // Счётчик для определения общего количества айтемов
        double count = 0.0;
        for (Tuple el : list
        ) {
            //Считаем количество
            count +=  Double.parseDouble(el.get(1).toString()) ;
        }

        HashMap<String , Double> hashMap = new HashMap<>();

        for (Tuple el : list
        ) {
            // Кладём всё в мапу, для человекочитаемости умножаем на 100
            // так как 0.32 будет труден в понимании
            hashMap.put( el.get(0).toString(), Double.parseDouble(el.get(1).toString())  / count * 100);
        }

        return hashMap;
    }
}
