package jm.stockx.controller.user;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jm.stockx.components.templates.AreaOfLinkToBuyItemMostPopular;

@Route("test-template")
public class TestTemplate extends VerticalLayout {
    private AreaOfLinkToBuyItemMostPopular areaOfLinkToBuyItemMostPopular = new AreaOfLinkToBuyItemMostPopular(
            "Louis Vuitton Key Pouch Monogram Brown",
            "Lowest ask",
            "€ 939",
            "15 sold",
            "images-for-templates/Louis_Vuitton_Key_Pouch_Monogram_Brown.jpg",
            "");
    public TestTemplate() {
        add(areaOfLinkToBuyItemMostPopular);
    }
}
