package jm.stockx.components.how_it_works.hiw_footer;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class FooterRowHIWPage extends VerticalLayout {

    private Div footerLast = new Div();
    private Div footerGeneral = new Div(footerLast);

    public FooterRowHIWPage() {
        init();
    }

    private void init() {
        configureAdd();
        configElements();
        setStyleFooter();
    }

    private void configElements() {
        configureFooterLast();
        configureFooterGeneral();
    }

    private void configureFooterLast() {
        footerLast.setClassName("footer-last");
        footerLast.getStyle().set("background", "#2D2D2D");
        footerLast.setHeight("80px");
        footerLast.setWidth("100%");
        footerLast.getStyle().set("padding", "20px 0 0");
        footerLast.getStyle().set("position", "relative");
        footerLast.getStyle().set("top", "75%");

    }

    private void configureFooterGeneral() {
        footerGeneral.setClassName("footer-main");
        footerGeneral.setWidth("100%");
        footerGeneral.setHeight("400px");
    }

    private void setStyleFooter() {
        setClassName("footer-general");
        getStyle().set("background", "#3D3D3D");
        setWidth("100%");
        setPadding(false);
        getStyle().set("margin", "0");
    }

    private void configureAdd() {
        add(footerGeneral);
    }
}
