package jm.stockx.controller.user;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jm.stockx.components.templates.LatestNews;
import jm.stockx.components.templates.ProductTile;

@Route("test-template")
public class TestTemplate extends VerticalLayout {
    private ProductTile productTile = new ProductTile(
            "Louis Vuitton Key Pouch Monogram Brown",
            "Lowest ask",
            "€ 939",
            "15 sold",
            "images-for-templates/Louis_Vuitton_Key_Pouch_Monogram_Brown.jpg",
            "");

    private LatestNews latestNews = new LatestNews("#", "How to Follow StockX Bag Condition Guidelines",
            "StockX - 08/31/2020");

    public TestTemplate() {
        add(productTile, latestNews);
    }
}
