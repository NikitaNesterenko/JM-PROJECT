package jm.stockx.components.how_it_works.hiw_body;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class BodyRowHIWPage extends VerticalLayout {
    private Div headerContainer = new Div();
    private Div explanationContainer = new Div();
    private Div buyingContainer = new Div();
    private Div sellingContainer = new Div();
    private Div footerContainer = new Div();

    private Div worksWrapper = new Div(headerContainer,
            explanationContainer,
            buyingContainer,
            sellingContainer,
            footerContainer);

    public BodyRowHIWPage() {
        initBodyRowHIWPage();
    }

    private void initBodyRowHIWPage() {
        configureWorksWrapper();
        configureHeaderContainer();
        configureExplanationContainer();
        configureBuyingContainer();
        configureSellingContainer();
        configureFooterContainer();
        configureAdd();
        setStyleBody();
    }

    private void configureHeaderContainer() {
        headerContainer.setClassName("header-container");

        headerContainer.getStyle().set("background-image", "url(//stockx-assets.imgix.net/png/icons/works/background.png?auto=compress,format)");
        headerContainer.getStyle().set("background-repeat", "no-repeat");
        headerContainer.getStyle().set("background-size", "100%");
        headerContainer.getStyle().set("background-position-x", "center");
        headerContainer.setWidth("100%");
        headerContainer.setHeight("520px");
        headerContainer.getStyle().set("padding", "80px 0");
    }

    private void configureExplanationContainer() {
        explanationContainer.setClassName("explanation-container");
        explanationContainer.getStyle().set("background", "#FFFFFF");
        explanationContainer.setWidth("100%");
        explanationContainer.setHeight("420px");
        explanationContainer.getStyle().set("padding", "80px 0");

        Div container = new Div();
        container.setClassName("container");
        container.setHeight("100%");
        container.setWidth("75%");
        container.getStyle().set("margin", "0 auto");
        container.getStyle().set("padding", "0 15px");

        H2 title = new H2("THE BASICS");
        title.getStyle().set("text-align", "center");
        title.getStyle().set("font-size", "60px");
        title.getStyle().set("margin", "0");

        container.add(title);

        explanationContainer.add(container);
    }

    private void configureBuyingContainer() {
        buyingContainer.setClassName("buying-container");
        buyingContainer.getStyle().set("background-color", "#00bbe3");
        buyingContainer.setWidth("100%");
        buyingContainer.setHeight("420px");
        buyingContainer.getStyle().set("padding", "80px 0");

        Div container = new Div();
        container.setClassName("container");
        container.setHeight("100%");
        container.setWidth("75%");
        container.getStyle().set("margin", "0 auto");
        container.getStyle().set("padding", "0 15px");

        H2 title = new H2("BUYING ON STOCKX");
        title.getStyle().set("text-align", "left");
        title.getStyle().set("font-size", "60px");
        title.getStyle().set("margin", "0");

        container.add(title);

        buyingContainer.add(container);
    }

    private void configureSellingContainer() {
        sellingContainer.setClassName("selling-container");
        sellingContainer.getStyle().set("background-color", "#525252");
        sellingContainer.setWidth("100%");
        sellingContainer.setHeight("420px");
        sellingContainer.getStyle().set("padding", "80px 0");

        Div container = new Div();
        container.setClassName("container");
        container.setHeight("100%");
        container.setWidth("75%");
        container.getStyle().set("margin", "0 auto");
        container.getStyle().set("padding", "0 15px");

        H2 title = new H2("SELLING ON STOCKX");
        title.getStyle().set("text-align", "left");
        title.getStyle().set("font-size", "60px");
        title.getStyle().set("margin", "0");
        title.getStyle().set("color", "white");

        container.add(title);

        sellingContainer.add(container);
    }

    private void configureFooterContainer() {
        footerContainer.setClassName("footer-container");
        footerContainer.getStyle().set("background-color", "rgb(80, 158, 47)");
        footerContainer.setWidth("100%");
        footerContainer.setHeight("150px");

        Image footerImage = new Image("https://stockx-assets.imgix.net/now_you_know.png?auto=compress,format&h=150", "Footer image");
        footerImage.getStyle().set("display", "block");
        footerImage.getStyle().set("margin", "auto");

        footerContainer.add(footerImage);
    }

    private void configureWorksWrapper() {
        worksWrapper.getStyle().set("margin", "0");
        worksWrapper.setClassName("works-wrapper");
        worksWrapper.setWidth("100%");
        worksWrapper.setHeight("100%");
    }

    private void setStyleBody() {
        setMargin(false);
        setPadding(false);
        getStyle().set("background-color", "#000000");
        setWidth("100%");
    }

    private void configureAdd() {
        add(worksWrapper);
    }
}
