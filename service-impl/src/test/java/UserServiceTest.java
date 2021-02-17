import jm.stockx.UserServiceImpl;
import jm.stockx.api.dao.UserDaoImpl;
import jm.stockx.dto.user.UserDto;
import jm.stockx.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDaoImpl userDao;

    private final String firstName = "TestName";

    private final String lastName = "TestLastName";

    private final String email = "test@mail.ru";

    private final String username = "username";

    private final String password = "123";

    private final Byte sellerLevel = 1;

    private final Boolean vacationMode = false;

    private final String localeTag = "ru";

    @Mock
    private User mockUser;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserByName() {
        User user = new User(firstName, lastName, email, username, password, sellerLevel, vacationMode, localeTag);
        Mockito.when(userDao.getUserDtoByUserUsername(Mockito.anyString()))
                .thenReturn(new UserDto(user));
        UserDto fromTest = userService.getUserByUsername("name");
        Assert.assertEquals(username, fromTest.getUsername());
    }

    @Test
    public void getUserByEmailTest() {
        when(userDao.getUserDtoByUserEmail(any())).thenReturn(new UserDto(mockUser));
        assertEquals(userService.getUserDtoByUserEmail(any()), mockUser);
        verify(userDao).getUserDtoByUserEmail(any());
    }
}
