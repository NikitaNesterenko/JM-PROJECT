import jm.stockx.api.dao.SellingInfoDAO;
import jm.stockx.api.dao.SellingInfoDaoImpl;

public class Main {
    public static void main(String[] args) {
        SellingInfoDAO sid = new SellingInfoDaoImpl();
        Double min = sid.getMinSalesValue(1L);
    }
}
