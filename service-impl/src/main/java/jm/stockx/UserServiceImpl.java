package jm.stockx;

import jm.stockx.api.dao.UserDAO;
import jm.stockx.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    final UserDAO userDao;
    MailService mailService;

    @Autowired
    public UserServiceImpl(UserDAO userDao) {
        this.userDao = userDao;

    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userDao.getAll();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public void createUser(User user) {
        try {
            user.setActive(false);
            userDao.add(user);
            mailService.sendRegistrationLinkToUser(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

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
    public User getUserById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userDao.getByName(userName).orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getByEmail(email).orElse(null);
    }

    @Override
    public User getUserByAppleUserId(String appleId) {
        return userDao.getByAppleId(appleId).orElse(null);
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
}
