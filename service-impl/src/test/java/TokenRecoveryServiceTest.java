//import jm.stockx.TokenRecoveryServiceImpl;
//import jm.stockx.api.dao.TokenRecoveryDAO;
//import jm.stockx.entity.TokenRecovery;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//public class TokenRecoveryServiceTest {
//
//    @InjectMocks
//    private TokenRecoveryServiceImpl tokenService;
//
//    @Mock
//    private TokenRecoveryDAO tokenDao;
//
//    @Mock
//    private TokenRecovery token;
//
//    @Test
//    public void createTokenTest() {
//        tokenService.createToken(token);
//        verify(tokenDao).add(token);
//    }
//
//    @Test
//    public void deleteTokenTest() {
//        tokenService.deleteToken(1L);
//        verify(tokenDao).deleteById(1L);
//    }
//
//    @Test
//    public void getTokenByHashEmailTest() {
////        when(tokenDao.getTokenRecoveryByHashEmail(any())).thenReturn(token);
//        assertEquals(tokenService.getTokenRecoveryByHashEmail(any()), token);
//        verify(tokenDao).getTokenRecoveryByHashEmail(any());
//    }
//
//    @Test
//    public void getTokenByIdTest() {
//        when(tokenDao.getById(1L)).thenReturn(token);
//        assertEquals(tokenService.getTokenRecoveryDtoByTokenRecoveryId(1L), token);
//        verify(tokenDao).getById(1L);
//    }
//
//
//}
