import jm.stockx.MailServiceImpl;
import jm.stockx.RecoveryException;
import jm.stockx.TokenRecoveryService;
import jm.stockx.UserService;
import jm.stockx.dto.user.UserDto;
import jm.stockx.entity.TokenRecovery;
import jm.stockx.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyString;

@RunWith(SpringRunner.class)
public class MailServiceTest {

    @InjectMocks
    private MailServiceImpl mailService;

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private TokenRecoveryService tokenService;

    @Mock
    private UserService userService;

    @Test
    public void sendRecoveryLinkToUserTest() {
        UserDto user = new UserDto();
        user.setEmail("testEmail");
        assertTrue(mailService.sendRecoveryLinkToUser(user));
    }

    @Test
    public void doNotSendRecoveryLinkToNullUserTest() {
        assertFalse(mailService.sendRecoveryLinkToUser(null));
    }

    @Test
    public void doNotSendRecoveryLinkToNullEmailTest() {
        UserDto user = new UserDto();
        assertFalse(mailService.sendRecoveryLinkToUser(user));
    }

    @Test
    public void doNotChangePasswordIfLinkIsNotValidTest() throws RecoveryException {
        ReflectionTestUtils.setField(mailService, "urlRecoveryLink", "link");
        assertFalse(mailService.changePasswordByToken("anotherLink", anyString()));
    }

    @Test
    public void doNotChangePasswordIfTokenIsNotValidTest() throws RecoveryException {
        ReflectionTestUtils.setField(mailService, "urlRecoveryLink", "link");
        ReflectionTestUtils.setField(mailService, "linkExpirationDays", -1);
        TokenRecovery token = new TokenRecovery();
        token.setHashEmail("hashEmail");
        token.setStartTime(new Date());
//        TODO
//        when(tokenService.getTokenRecoveryByHashEmail("link")).thenReturn(token);
        assertFalse(mailService.changePasswordByToken("link", anyString()));
    }

    @Test
    public void changePasswordIfTokenIsValidTest() throws RecoveryException {
        ReflectionTestUtils.setField(mailService, "urlRecoveryLink", "link");
        ReflectionTestUtils.setField(mailService, "linkExpirationDays", 1);
        TokenRecovery token = new TokenRecovery();
        token.setUser(new User());
        token.setHashEmail("hashEmail");
        token.setStartTime(new Date());
//        TODO
//        when(tokenService.getTokenRecoveryByHashEmail("link")).thenReturn(token);
        assertTrue(mailService.changePasswordByToken("link", anyString()));
    }
}
