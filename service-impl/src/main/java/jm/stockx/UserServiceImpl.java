package jm.stockx;

import jm.stockx.api.dao.UserDAO;
import jm.stockx.dto.item.ItemPurchaseDto;
import jm.stockx.dto.user.UserDto;
import jm.stockx.dto.user.UserPutDto;
import jm.stockx.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
       // user.setUserLevel(new UserLevel(1, 0));
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
    public User getUserByEmail(String email) throws UserNotFoundException {
        User user = userDao.getUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public void updateUserFromDto(UserPutDto userPutDto, @AuthenticationPrincipal User principal) throws UserNotFoundException, ForbiddenException {
        if (!userDao.doesItExistEntity(userPutDto.getId())) {
            throw new UserNotFoundException();
        }
        if (!principal.getId().equals(userPutDto.getId())) {
            throw new ForbiddenException();
        }
        userDao.updateUserFromDto(userPutDto);
    }

    public Map<String, Double> getPurchaseStatisticsPercentageByUserId(Long id) {
        List<ItemPurchaseDto> list = userDao.getPurchaseStatisticsByUserId(id);

        int count = list.stream().flatMapToInt(el -> IntStream.of(el.getCount())).sum();

        return list.stream().collect(Collectors.toMap(ItemPurchaseDto::getItemCategory, x -> x.getCount().doubleValue() * 100 / count));
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        return userDao.isUserExistByEmail(email);
    }
}
