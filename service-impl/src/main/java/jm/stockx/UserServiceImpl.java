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

import java.util.Optional;
import java.util.UUID;

import org.springframework.util.StringUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDao;

    @Autowired
    private MailSender mailSender;

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
        try{
            Optional<User> userFromDb = userDao.getByName(user.getUsername());
        } catch (Exception e) {
            return;
        }



//        if (userFromDb.isPresent()) {
//            return;
//        }

        user.setActive(true);
//        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        userDao.add(user);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to App. Please, visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);
        }

    }

    @Override
    public boolean activateUser(String code) {
        User user = userDao.getByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);

        userDao.update(user);

        return true;
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
