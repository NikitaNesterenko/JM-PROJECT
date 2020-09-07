package jm.stockx.controller.view.head_form;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;


@CssImport("./styles/css/headRow.css")
public class HeadRowSite extends Div {
    Div searchDiv = new Div();
    Div headerMenu = new Div();
    Div logo = new Div();
    Div searchBlock = new Div();
    Image image = new Image("images/logoStockx.png", "Tipa stockx logo");
    Button searchButton = new Button(new Icon(VaadinIcon.SEARCH));
    Input searchField = new Input();
    HeadMenu headMenu = new HeadMenu();

    public HeadRowSite() {
        initHeadRowSite();
        initClass();
        add(logo, searchDiv, headerMenu);
    }

    private void initHeadRowSite() {
        logo.add(image);
        searchBlock.add(searchButton, searchField);
        searchDiv.add(searchBlock);
        headerMenu.add(headMenu);
        searchField.setPlaceholder("Search for brands, color, etc.");
    }

    private void initClass() {
        this.addClassNames("header_style", "content_style");
        searchDiv.addClassName("search_field_style");
        logo.addClassName("logo_style");
        headMenu.addClassName("head_menu_style");
        headerMenu.addClassName("header_menu_style");
        searchBlock.addClassName("search_block_style");
    }
}
