package jm.stockx.components.templates;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.BoxSizing;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class LatestNews extends VerticalLayout {
    private Anchor newsAnchor;
    private Label detailsLabel = new Label();

    public LatestNews(String newsUrl, String newsTitle, String details) {
        this.newsAnchor = new Anchor(newsUrl, newsTitle);
        this.detailsLabel.add(details);
        configureMainLayout();
    }

    public void configureMainLayout() {
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        mainLayout.setBoxSizing(BoxSizing.BORDER_BOX);
        mainLayout.getStyle().set("border", "1px solid #F8F7F7");
        mainLayout.getStyle().set("max-width", "500px");
        mainLayout.setHeight("100px");

        newsAnchor.getStyle().set("font-size", "18pt");
        newsAnchor.getStyle().set("color", "#000000");
        newsAnchor.getStyle().set("text-decoration", "none");
        newsAnchor.getStyle().set("font-weight", "bold");
        newsAnchor.getStyle().set("font-family", "CALIBRI");

        detailsLabel.getStyle().set("font-size", "13pt");
        newsAnchor.getStyle().set("font-family", "CALIBRI");

        mainLayout.add(newsAnchor, detailsLabel);
        mainLayout.setPadding(false);
        mainLayout.setSpacing(false);
        add(mainLayout);
    }
}
