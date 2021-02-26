package jm.stockx;

import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.ItemInfo;
import jm.stockx.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LetterServiceImpl  implements LetterService{
    private final ItemInfoService itemInfoService;

    @Autowired
    public LetterServiceImpl(ItemInfoService itemInfoService) {
        this.itemInfoService = itemInfoService;
    }

    @Override
    public String createBuyingLetter(User user, BuyingInfo buyingInfo) {
        String date1 = buyingInfo.getBuyingTimeStamp().toLocalDate().toString();
        String date2 = buyingInfo.getBuyingTimeStamp().toLocalDate().plusDays(10L).toString();

        String head = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "</head>";

        String style = "<style>\n" +
                "  .grid-container {\n" +
                "  display: grid;\n" +
                "  grid-template-areas: \"post-6 post-5\"; \n" +
                "  grid-template-rows: repeat(3, 100px);\n" +
                "  grid-template-columns: repeat(1, 1fr);\n" +
                "  border: 2px solid rgb(128, 128, 128, 30%);\n" +
                "}\n" +
                ".post-5 {\n" +
                "  grid-area: post-5;\n" +
                "}\n" +
                ".post-6 {           \n" +
                "  grid-area: post-6;\n" +
                "}\n" +
                "</style>";

        String body1 = "<body style=\"width: 30%;\">\n" +
                "<div style=\"font-size: 15px; height: 20%; background: green; display: flex;\">\n" +
                "<img src=\"cid:StockXLogo\" style=\"width: 80px; height: 60px; display: inline-block;\" alt=\"\">\n" +
                "<p style=\"margin-bottom: 0%; margin-left: 5px; color: white; font-size: 15px; background-color: green; display: inline-block;\">ESTIMATED ARRIVAL<br>  AT YOUR DOOR:</p>\n" +
                "<p style=\"margin-top: auto; margin-left: 5px; display: flex; font-size: 25px; color: white; background-color: green; margin-bottom: auto; display: inline-block;\">\n" +
                date1 + " - " + date2 +
                "</p>\n" +
                "</div>";

        String status;
        switch (buyingInfo.getStatus()){
            case ACCEPTED:
                status = "<div style=\"background: green; font-size: 30px; text-align: center;\">\n" +
                        "        <font color= \"white\";>&#10102;</font>&mdash;&mdash;&mdash; &#10103; &mdash;&mdash;&mdash; &#10104;\n" +
                        "        <br><font color= \"white\";>Accepted</font>  -  Delivering  -  Delivered\n" +
                        "    </div>";
                break;
            case DELIVERING:
                status = "<div style=\"background: green; font-size: 30px; text-align: center;\">\n" +
                        "        &#10102; &mdash;&mdash;&mdash; <font color= \"white\";>&#10103;</font> &mdash;&mdash;&mdash; &#10104;\n" +
                        "        <br>Accepted  -  <font color= \"white\";>Delivering</font>  -  Delivered\n" +
                        "    </div>";
                break;
            case DELIVERED:
                status = "<div style=\"background: green; font-size: 30px; text-align: center;\">\n" +
                        "        &#10102; &mdash;&mdash;&mdash; &#10103; &mdash;&mdash;&mdash; <font color= \"white\";>&#10104;</font>\n" +
                        "        <br>Accepted  -  Delivering  -  <font color= \"white\";>Delivered</font>\n" +
                        "    </div>";
                break;
            default:
                status = "<div style=\"background: green; font-size: 30px; text-align: center;\">\n" +
                        "        &#10102; &mdash;&mdash;&mdash; &#10103; &mdash;&mdash;&mdash; &#10104;\n" +
                        "        <br>Accepted  -  Delivering  -  Delivered\n" +
                        "    </div>";                
        }

        String body2 = "<div>\n" +
                "        <h1 style=\"text-align: center;\">Order Confirmation</h1>\n" +
                "        <p style=\"text-align: left;\">Congrats! Your latest StockX purchase is on the way. You can expect to receive it by " + date2 + ".\n" +
                "            To check the current status and delivery tracking of your order, visit <a href=\"http://localhost:4446/\"\n" +
                "                style=\"color: yellowgreen; text-decoration: none;\">stockx.com/buying.</a></p>\n" +
                "        <p style=\"text-align: left;\">Due to the global impact of COVID-19, your order may run into unexpected delays.\n" +
                "        </p>\n" +
                "    </div>";

        StringBuilder items = new StringBuilder();

        for (ItemInfo i:buyingInfo.getBoughtItemsInfo()) {
            items.append("    <div style=\"display: grid;\" class=\"grid-container\">\n" +
                    "        <img src=\"" + i.getItemImageUrl() + "\" alt=\"\" style=\"width: 200px; height: 200px;\" class=\"post-6\">\n" +
                    "\n" +
                    "        <div style=\"margin-left: auto; position: relative\" class=\"post-5\">\n" +
                    "            <a href=\""+ "http://localhost:4446/item?id=" + i.getItem().getId() + "\" style=\"color: yellowgreen; font-size: 20px; text-decoration: none;\">" +
                                    i.getItem().getName() + "</a>\n" +
                    "            <p>U.S. Unisex Size: " + i.getSize() + "\n" +
                    "                <br>Condition: " + i.getCondition() + "\n" +
                    "                <br>Order number: ??? \n" +
                    "                <br>Purchase Price: " + i.getPrice() + "\n" +
                    "                <br>Fee: $6.90\n" +
                    "                <br>Shipping: $40\n" +
                    "                <br>________________________\n" +
                    "            <br>TOTAL PAYMENT: $276.90\n" +
                    "            <br><button onclick=\"" + "http://localhost:4446/order?id=" + buyingInfo.getId() + "\" style=\"background: black; color: white;\">View Order</button>\n" +
                    "            </p>\n" +
                    "        </div>\n" +
                    "    </div>");
        }

        String footer = "<div>\n" +
                "        <p style=\"padding-left: 5%; font-size: 20px;\">FREQUENTLY ASKED QUESTIONS</p>\n" +
                "        <ul>\n" +
                "            <a href=\"\" style=\"color: yellowgreen; text-decoration: none; font-size: 20px;\">\n" +
                "                <li>How long does it take to receive my order?</li>\n" +
                "            </a>\n" +
                "            <br><a href=\"\" style=\"color: yellowgreen; text-decoration: none; font-size: 20px;\">\n" +
                "                <li>My Bid just got accepted. Can i cancel it?</li>\n" +
                "            </a>\n" +
                "            <br><a href=\"\" style=\"color: yellowgreen; text-decoration: none; font-size: 20px;\">\n" +
                "                <li>Can i change my address if my Bid is accepted?</li>\n" +
                "            </a>\n" +
                "        </ul>\n" +
                "    </div>" +
                "    <div style=\"background: green; text-align: center; color: white; padding: 10px;\">\n" +
                "        <a href=\"\" style=\"color: white; background-color: green; text-decoration: none;\">Email Settings</a> | <a href=\"\"\n" +
                "            style=\"color: white; background-color: green; text-decoration: none;\">Help</a> | <a href=\"\"\n" +
                "            style=\"color: white; background-color: green; text-decoration: none;\">Jobs</a>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";

        return head + style + body1 + status + body2 + items + footer;
    }
}
