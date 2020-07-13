import jm.TokenRecovery;
import jm.TokenRecoveryServiceImpl;
import jm.api.dao.TokenRecoveryDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class TokenRecoveryServiceTest {

    @InjectMocks
    private TokenRecoveryServiceImpl tokenService;

    @Mock
    private TokenRecoveryDAO tokenDao;

    @Mock
    private TokenRecovery token;

    @Test
    public void createTokenTest() {
        tokenService.createToken(token);
        verify(tokenDao).add(token);
    }

    @Test
    public void deleteTokenTest() {
        tokenService.deleteToken(1L);
        verify(tokenDao).deleteById(1L);
    }

    @Test
    public void getTokenByHashEmailTest() {
        when(tokenDao.getByHashEmail(any())).thenReturn(token);
        assertEquals(tokenService.getTokenByHashEmail(any()), token);
        verify(tokenDao).getByHashEmail(any());
    }

    @Test
    public void getTokenByIdTest() {
        when(tokenDao.getById(1L)).thenReturn(token);
        assertEquals(tokenService.getTokenById(1L), token);
        verify(tokenDao).getById(1L);
    }


}
