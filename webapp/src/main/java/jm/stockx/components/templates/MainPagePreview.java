package jm.stockx.components.templates;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

@CssImport("./styles/main-page-view.css")
public class MainPagePreview extends VerticalLayout {

    private final VerticalLayout layout = new VerticalLayout();
    private final Label label1 = new Label("Buy & Sell");
    private final Label label2 = new Label("Authentic Sneakers");

    public MainPagePreview() {
        Button searchButton = new Button("",new Icon(VaadinIcon.SEARCH));
        searchButton.addThemeVariants();

        TextField textField = new TextField();
        textField.setPlaceholder("Search....");
        textField.setClearButtonVisible(true);
        textField.setWidth("20%");
        textField.setHeight("400%");
        textField.setSuffixComponent(searchButton);

        layout.add(label1);
        layout.add(label2);
        layout.add(textField);

        add(layout);
        configureLayout();
    }

    public void configureLayout(){
        layout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        label1.getStyle().set("font-size", "43pt");
        label1.getStyle().set("font-color","black");

        label2.getStyle().set("font-size", "43pt");
        label2.getElement().getStyle().set("color", "white");
        label2.getStyle().set("background-color","black");

        layout.getStyle().set("background-image", "url(//stockx-assets.imgix.net/Core/sneaker_plain.png?auto=compress,format)");
        layout.getStyle().set("background-repeat", "no-repeat");
        layout.getStyle().set("background-size", "100%");
        layout.getStyle().set("background-position-x", "center");
        layout.setWidth("100%");
        layout.setHeight("620px");
        layout.getStyle().set("padding", "80px 0");
    }
}
