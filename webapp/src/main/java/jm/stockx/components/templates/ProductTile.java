package jm.stockx.components.templates;

import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.BoxSizing;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ProductTile extends VerticalLayout {
    private Div imgDiv = new Div();
    private Div textDescriptionItemDiv = new Div();
    private Div bidDiv = new Div();
    private Image itemLogoImg;
    private Label itemTitle = new Label();
    private Label itemDemand = new Label();
    private Label bid = new Label();
    private Label latestSaleInfo = new Label();

    public ProductTile(String itemTitle, String itemDemand, String bid, String latestSaleInfo,
                       String urlImage, String dummyImage) {
        this.itemTitle.add(itemTitle);
        this.itemDemand.add(itemDemand);
        this.bid.add(bid);
        this.latestSaleInfo.add(latestSaleInfo);
        this.itemLogoImg = new Image(urlImage, dummyImage);

        configureMainLayout();
    }

    private void configureMainLayout() {
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        mainLayout.setBoxSizing(BoxSizing.BORDER_BOX);
        mainLayout.getStyle().set("border", "1px solid #F8F7F7");
        mainLayout.getStyle().set("max-width", "200px");
        mainLayout.setHeight("300px");

        configureImgDiv();
        configureTextDescriptionItemDiv();

        mainLayout.add(imgDiv,textDescriptionItemDiv);
        mainLayout.setPadding(false);
        mainLayout.setSpacing(false);
        add(mainLayout);
    }

    private void configureImgDiv() {
        imgDiv.setHeight("55%");

        VerticalLayout layoutImgDiv = new VerticalLayout();
        layoutImgDiv.add(itemLogoImg);
        layoutImgDiv.setHorizontalComponentAlignment(Alignment.CENTER, itemLogoImg);
        layoutImgDiv.setJustifyContentMode(JustifyContentMode.CENTER);

        imgDiv.add(layoutImgDiv);
    }

    private void configureTextDescriptionItemDiv() {
        textDescriptionItemDiv.getStyle().set("background", "#FCFCFC");
        textDescriptionItemDiv.setHeight("45%");

        itemTitle.getStyle().set("font-size", "13pt");
        itemTitle.getStyle().set("font-weight", "bold");
        itemTitle.getStyle().set("font-family", "CALIBRI");
        itemTitle.getStyle().set("line-height", "0.9em");

        configureBidDiv();

        latestSaleInfo.getStyle().set("font-size", "12pt");
        latestSaleInfo.getStyle().set("font-family", "CALIBRI");

        VerticalLayout layoutTextDescriptionItemDiv = new VerticalLayout();
        layoutTextDescriptionItemDiv.setSpacing(false);
        layoutTextDescriptionItemDiv.add(itemTitle, bidDiv,latestSaleInfo);
        layoutTextDescriptionItemDiv.setHorizontalComponentAlignment(Alignment.CENTER, itemLogoImg);
        layoutTextDescriptionItemDiv.setJustifyContentMode(JustifyContentMode.CENTER);

        textDescriptionItemDiv.add(layoutTextDescriptionItemDiv);
    }

    private void configureBidDiv() {
        itemDemand.getStyle().set("font-size", "12pt");
        itemDemand.getStyle().set("font-family", "CALIBRI");
        bid.getStyle().set("font-size", "20pt");
        bid.getStyle().set("font-weight", "bold");
        bid.getStyle().set("font-family", "CALIBRI");
        bid.getStyle().set("line-height", "0.8em");

        VerticalLayout layoutBidDiv = new VerticalLayout();
        layoutBidDiv.setSpacing(false);
        layoutBidDiv.setPadding(false);
        layoutBidDiv.add(itemDemand,bid);

        bidDiv.add(layoutBidDiv);
    }
}
