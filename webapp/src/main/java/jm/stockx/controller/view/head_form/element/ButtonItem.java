package jm.stockx.controller.view.head_form.element;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;

@Route("button")
@CssImport("./styles/css/buttonItem.css")
public class ButtonItem extends Div {
    Div topRow = new Div();
    Div bottomRow = new Div();

    ButtonItem() {
        this("600", "Ask", "Heistest Bid", "Low");
    }

    public ButtonItem(String tle, String tre, String ble, String bre) {
        Span left = new Span(tre);
        Span left2 = new Span(new Span("or" + bre));
        topRow = new Div(new Span("$" +tle), left);
        bottomRow = new Div(new Span(ble), left2);
        left.addClassName("border");
        left2.addClassName("border");
        initClass();
        add(topRow, bottomRow);

    }

    private void initClass() {
        this.addClassName("button_item_style");
        topRow.addClassName("top_row_style");
        bottomRow.addClassName("bottom_row_style");
    }


}
