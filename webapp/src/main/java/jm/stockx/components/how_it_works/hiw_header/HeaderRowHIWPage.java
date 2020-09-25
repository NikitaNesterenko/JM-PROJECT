package jm.stockx.components.how_it_works.hiw_header;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class HeaderRowHIWPage extends HorizontalLayout {
    private Anchor logoLink = new Anchor();
    private Div navbarSearch = new Div();

    private HorizontalLayout navbarInner = new HorizontalLayout(logoLink);

    public HeaderRowHIWPage() {
        initHeaderRowHIWPage();
    }

    private void initHeaderRowHIWPage(){
        configureNavbarInner();
        configureLogo();
//        configureNavbarSearch();
        configureAdd();
        setStyleHeader();
    }



    private void configureLogo() {
//        I способ получения картинки
//        byte[] imageByte = new byte[3276];
//        StreamResource streamResource = new StreamResource("StockXLogo.png", () -> new ByteArrayInputStream(imageByte));
//        Image imageLogo = new Image(streamResource, "StockX");

//        II способ получения картинки
        Image imageLogo = new Image("https://stockx-assets.imgix.net/logo/stockx_homepage_logo_dark.svg?auto=compress,format", "StockX");
        imageLogo.setWidth("50%");

        logoLink.add(imageLogo);
        logoLink.setWidth("200px"); //width: 200px;
        logoLink.setHeight("90px"); //height: 90px;
        logoLink.getStyle().set("display", "flex");
        logoLink.getStyle().set("justify-content", "center");
    }

    private void configureNavbarSearch() {
        Icon searchIcon = new Icon(VaadinIcon.SEARCH);
        searchIcon.getStyle().set("width", "16px");
        searchIcon.getStyle().set("height", "16px");
        searchIcon.getStyle().set("margin","0");
//        Input searchField = new Input();
//        searchField.setPlaceholder("Search for brand, color, etc.");
//        searchField.getStyle().set("border", "none");

        TextField textArea = new TextField();
        textArea.setPlaceholder("Search for brand, color, etc.");

        navbarSearch.setHeight("44px");
        navbarSearch.setMinWidth("768px");
        navbarSearch.getStyle().set("background-color", "#f6f6f6");
        navbarSearch.getStyle().set("padding", "6px 15px 6px 45px");
        navbarSearch.add(searchIcon, textArea);
    }

    private void setStyleHeader() {
        setClassName("footer");
        getStyle().set("background-color", "#EAFFB7");
        getStyle().set("margin", "0");
        setWidth("100%");
        getStyle().set("position","fixed");
    }

    private void configureAdd() {
        add(navbarInner);
    }

    private void configureNavbarInner() {
        navbarInner.setDefaultVerticalComponentAlignment(Alignment.CENTER);

    }
}
