package jm.stockx.controller.view.item_page;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import jm.stockx.controller.view.head_form.HeadRowSite;

@Route("")
public class ItemPage extends Div {
    HeadRowSite headRowSite = new HeadRowSite();
    ItemView itemView = new ItemView();

    public ItemPage() {
        add(headRowSite, itemView);
    }
}
