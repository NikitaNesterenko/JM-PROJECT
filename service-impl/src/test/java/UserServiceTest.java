import jm.User;
import jm.UserServiceImpl;
import jm.dao.UserDaoImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserByName() {
        User user = new User(firstName, lastName, email, username, password, sellerLevel, vacationMode);
        Mockito.when(userDao.getUserByUsername(Mockito.anyString()))
                .thenReturn(java.util.Optional.of(user));
        User fromTest = userService.getUserByUserName("name");
        Assert.assertEquals(username, fromTest.getUsername());
    }
}
