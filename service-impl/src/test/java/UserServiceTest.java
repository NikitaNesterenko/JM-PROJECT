import jm.User;
import jm.UserServiceImpl;
import jm.dao.UserDaoImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
public class UserServiceTest {


    @InjectMocks
    private UserDaoImpl userDao;

    @Mock
    private EntityManager entityManager;

//    public UserServiceTest() {
//        MockitoAnnotations.initMocks(this);
//
//    }

    private final String firstName = "TestName";

    private final String lastName = "TestLastName";

    private final String email = "test@mail.ru";

    private final String username = "username";

    private final String password = "123";

    private final Integer sellerLevel = 1;

    private final boolean vacationMode = false;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddUser() {
        User user = new User(firstName, lastName, email, username, password, sellerLevel, vacationMode);
        List<User> list = new ArrayList<>();
        list.add(user);
        userDao.addUser(user);
        Mockito.when(userDao.getAll())
                .thenReturn(null);

//        User fromServer = ;

//        if ((!fromServer.getFirstName().equals(firstName)) ||
//                !fromServer.getLastName().equals(lastName))
//            Assert.fail("Ожидаемый пользователь отличается от добавленного");
//        Assert.fail("При тестировании произошла ошибка");

    }
}
