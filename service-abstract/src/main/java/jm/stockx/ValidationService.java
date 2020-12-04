package jm.stockx;

public interface ValidationService {
    public boolean validateUserDB(String email) throws AuthorizationAdviceException;
}
