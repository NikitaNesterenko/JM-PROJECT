package jm.stockx;

import jm.stockx.dto.buyinginfo.BuyingInfoPostDto;
import jm.stockx.dto.user.UserDto;
import jm.stockx.dto.user.UserPutDto;
import jm.stockx.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getAllUsers();

    void createUser(User user);

    void deleteUser(Long id);

    void updateUser(User user);

    UserDto getUserDtoByUserId(Long id);

    UserDto getUserDtoByUserUsername(String username);

    UserDto getUserDtoByUserEmail(String email);

    UserDto getUserDtoByUserAppleId(String appleId);

    void login(String username, String password, Collection<? extends GrantedAuthority> authorities);

    boolean isUserExist(Long id);

    UserDto getUserByUsername(String username);

    UserDto getUserDtoById(Long id);

    User getUserById(Long id);

    UserDto getUserDtoByEmail(String email);

    User getUserByEmail(String email);

    void updateUserFromDto(UserPutDto userPutDto, @AuthenticationPrincipal User principal) throws UserNotFoundException, ForbiddenException;

    Map<String, Double> getPurchaseStatisticsPercentageByUserId(Long id);

    boolean isUserExistByEmail(String email);

    void addBuyingInfo(BuyingInfoPostDto buyingInfoPostDto);

}
