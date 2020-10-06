package jm.stockx.components.release_calendar;

import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import jm.stockx.dto.ReleaseItemDto;
import lombok.NoArgsConstructor;

import java.time.format.TextStyle;
import java.util.Locale;

@NoArgsConstructor
public class ReleaseCalendarCell extends Div {
    private ReleaseItemDto releaseItemDto;

    public ReleaseCalendarCell(ReleaseItemDto releaseItemDto) {
        this.releaseItemDto = releaseItemDto;
        createCell();
    }

    private void createCell() {
        Span verticalLine = new Span(" | ");
        verticalLine.getStyle().set("color", "#999");

        Div date = new Div();
        date.getStyle().set("letter-spacing", "-1");
        date.getStyle().set("font-size", "20px");
        date.add(releaseItemDto.getItemReleaseDate().getMonth().getDisplayName(TextStyle.SHORT_STANDALONE, Locale.ENGLISH));
        date.add(verticalLine);
        date.add(String.valueOf(releaseItemDto.getItemReleaseDate().getDayOfMonth()));


        Icon addIcon = new Icon(VaadinIcon.PLUS_CIRCLE_O);
        addIcon.setColor("black");
        addIcon.getStyle().set("margin", "-3px 0 0");
        addIcon.setSize("20px");

        Anchor addToCollection = new Anchor("#");
        addToCollection.getStyle().set("margin", "auto 0");
        addToCollection.getStyle().set("size", "20px");
        addToCollection.add(addIcon);


        Div releaseDate = new Div();
        releaseDate.getStyle().set("display", "flex");
        releaseDate.getStyle().set("justify-content", "space-between");
        releaseDate.getStyle().set("margin", "0 0 5px");
        releaseDate.add(date, addToCollection);


        Image itemImage = new Image(releaseItemDto.getItemImgUrl(), "Item image");
        itemImage.getStyle().set("max-width", "100%");


        Paragraph itemName = new Paragraph(releaseItemDto.getItemName());
        itemName.getStyle().set("font-size", "16px");
        itemName.getStyle().set("line-height", "16px");
        itemName.getStyle().set("margin", "0");

        Icon chevron = new Icon(VaadinIcon.CHEVRON_DOWN);
        chevron.getStyle().set("padding", "0 0 4px");
        chevron.setColor("red");
        chevron.setSize("16px");

        Paragraph itemPrice = new Paragraph();
        itemPrice.getStyle().set("margin", "10px 0 0");
        itemPrice.getStyle().set("font-size", "13px");
        itemPrice.getStyle().set("line-height", "13px");
        itemPrice.getStyle().set("letter-spacing", "1px");
        itemPrice.add(chevron);
        itemPrice.add(" ASK: $");
        itemPrice.add(String.valueOf(releaseItemDto.getItemLowestAsk().getAmountMajorLong()));


        Anchor itemPage = new Anchor("#");
        itemPage.getStyle().set("color", "black");
        itemPage.getStyle().set("text-decoration", "none");
        itemPage.add(itemName, itemPrice);


        Anchor buy = new Anchor("#");
        buy.getStyle().set("color", "#045732");
        buy.getStyle().set("display", "inline-block");
        buy.getStyle().set("text-decoration", "none");
        buy.getStyle().set("font-size", "17px");
        buy.getStyle().set("padding", "7px");
        buy.getStyle().set("border-style", "solid");
        buy.getStyle().set("border-width", "2px");
        buy.add("BID");


        getStyle().set("padding", "15px 20px 10px");
        getStyle().set("width", "200px");
        getStyle().set("height", "300px");
        getStyle().set("font-weight", "700");
        add(releaseDate, itemImage, itemPage, buy);
    }
}
