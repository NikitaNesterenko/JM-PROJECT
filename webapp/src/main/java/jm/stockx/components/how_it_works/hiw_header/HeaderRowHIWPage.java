package jm.stockx.components.how_it_works.hiw_header;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class HeaderRowHIWPage extends HorizontalLayout {
    private Anchor logoLink = new Anchor();

    private HorizontalLayout navbarInner = new HorizontalLayout(logoLink);

    public HeaderRowHIWPage() {
        initHeaderRowHIWPage();
    }

    private void initHeaderRowHIWPage() {
        configureNavbarInner();
        configureLogo();
        configureAdd();
        setStyleHeader();
    }


    private void configureLogo() {
        Image imageLogo = new Image("https://stockx-assets.imgix.net/logo/stockx_homepage_logo_dark.svg?auto=compress,format", "StockX");
        imageLogo.setWidth("50%");

        logoLink.add(imageLogo);
        logoLink.setWidth("200px"); //width: 200px;
        logoLink.setHeight("90px"); //height: 90px;
        logoLink.getStyle().set("display", "flex");
        logoLink.getStyle().set("justify-content", "center");
    }


    private void setStyleHeader() {
        setClassName("footer");
        getStyle().set("background-color", "#EAFFB7");
        getStyle().set("margin", "0");
        setWidth("100%");
        getStyle().set("position", "fixed");
    }

    private void configureAdd() {
        add(navbarInner);
    }

    private void configureNavbarInner() {
        navbarInner.setDefaultVerticalComponentAlignment(Alignment.CENTER);

    }
}
