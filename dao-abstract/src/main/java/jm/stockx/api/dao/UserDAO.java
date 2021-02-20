package jm.stockx.api.dao;

import jm.stockx.dto.item.ItemPurchaseDto;
import jm.stockx.dto.user.UserDto;
import jm.stockx.dto.user.UserEmailDto;
import jm.stockx.dto.user.UserPutDto;
import jm.stockx.entity.User;
import jm.stockx.enums.ItemCategory;

import java.util.List;

public interface UserDAO extends GenericDao<User, Long> {
    UserDto getUserDtoByUserUsername(String name);

    UserDto getUserDtoByUserEmail(String name);

    UserDto getUserDtoByUserAppleId(String appleId);

    UserDto getUserDtoByUserId(Long id);

    UserDto getUserDtoByUsername(String username);

    User getUserById(Long id);

    UserDto getUserDtoById(Long id);

    UserDto getUserDtoByEmail(String email);

    User getUserByEmail(String email);

    void updateUserFromDto(UserPutDto userPutDto);
  
    List<ItemPurchaseDto> getPurchaseStatisticsByUserId(Long id);

    boolean isUserExistByEmail(String email);

    List<UserEmailDto> getUserEmailDtoByItemCategory(ItemCategory itemCategory);
}
