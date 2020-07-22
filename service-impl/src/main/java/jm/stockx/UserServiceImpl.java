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

    private final UserDAO userdao;

    @Autowired
    public UserServiceImpl(UserDAO userdao) {
        this.userdao = userdao;
    }


    @Override
    public List<User> getAllUsers() {
        return userdao.getAll();
    }

    @Override
    public void createUser(User user) {
        userdao.add(user);
    }

    @Override
    public void deleteUser(Long id) {
        userdao.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        userdao.update(user);
    }

    @Override
    public User getUserById(Long id) {
        return userdao.getById(id);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userdao.getByName(userName).orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return userdao.getByEmail(email).orElse(null);
    }

    @Override
    public void login(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(username, password, authorities);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authReq);
    }
}
