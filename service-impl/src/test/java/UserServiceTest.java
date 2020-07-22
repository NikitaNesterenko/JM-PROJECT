import jm.stockx.UserServiceImpl;
import jm.stockx.api.dao.UserDaoImpl;
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

    private final byte sellerLevel = 1;

    private final boolean vacationMode = false;

    @Mock
    private User mockUser;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserByName() {
        User user = new User(firstName, lastName, email, username, password, sellerLevel, vacationMode);
        Mockito.when(userDao.getByName(Mockito.anyString()))
                .thenReturn(java.util.Optional.of(user));
        User fromTest = userService.getUserByUserName("name");
        Assert.assertEquals(username, fromTest.getUsername());
    }

    @Test
    public void getUserByEmailTest() {
        when(userDao.getByEmail(any())).thenReturn(java.util.Optional.of(mockUser));
        assertEquals(userService.getUserByEmail(any()), mockUser);
        verify(userDao).getByEmail(any());
    }
}
