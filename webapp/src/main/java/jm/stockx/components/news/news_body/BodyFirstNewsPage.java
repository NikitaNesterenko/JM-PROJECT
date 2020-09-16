package jm.stockx.components.news.news_body;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;

public class BodyFirstNewsPage extends Div {
    private Div newsDiv = new Div();
    private Div newsDescription = new Div();
    private Div newsDescriptionDiv = new Div();
    private Div newsLabelDiv = new Div();
    private Div newsPictureDiv = new Div();

    public BodyFirstNewsPage() {
        initFirstNews();
        add(newsDiv);
    }

    private void configureNewsLabel() {
        H1 label = new H1();
        label.add("Back To Cool:");
        label.add(" The Essentials");
        label.getStyle().set("font-size", "100px");
        label.getStyle().set("max-width", "780px");
        label.getStyle().set("margin", "0 auto");
        label.getStyle().set("padding", "55px 0 0");
        label.getStyle().set("text-align", "center");
        label.getStyle().set("line-height", ".84");
        newsLabelDiv.add(label);
    }

    private void configureNewsPicture() {
        Image firstNewsPic = new Image("news-page-images/FallEssentials_bloghero.jpg", "black-white-nike");
        firstNewsPic.getStyle().set("margin", "50px 0 0 0");

        newsPictureDiv.getStyle().set("justify-content", "space-between");
        newsPictureDiv.add(firstNewsPic);
    }

    private void configureNewsDescription() {
        H1 desc1 = new H1("Essentials For Going Back To Cool");
        desc1.getStyle().set("margin", "120px 0 0 0");
        desc1.getStyle().set("font-size", "60px");
        desc1.getStyle().set("line-height", "1.105");

        Paragraph desc2 = new Paragraph("It’s never too early to look ahead, and even though it’s the height of summer we think it’s time to get prepared to go Back to Cool.");
        desc2.getStyle().set("margin", "30px 0 0 0");

        newsDescription.getStyle().set("padding", "0 0 0 42px");
        newsDescription.add(desc1, desc2);
    }

    private void configureNewsDescriptionDiv() {
        configureNewsPicture();
        configureNewsDescription();

        newsDescriptionDiv.getStyle().set("display", "flex");
        newsDescriptionDiv.getStyle().set("margin", "0 auto");
        newsDescriptionDiv.getStyle().set("width", "75%");
        newsDescriptionDiv.add(newsPictureDiv, newsDescription);
    }

    private void initFirstNews() {
        configureNewsDescriptionDiv();

        configureNewsLabel();

        newsDiv.add(newsLabelDiv, newsDescriptionDiv);
    }
}
