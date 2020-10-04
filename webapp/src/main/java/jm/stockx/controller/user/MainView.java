package jm.stockx.controller.user;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Panel;
import jm.stockx.components.templates.MainPagePreview;
import org.vaadin.addons.searchbox.SearchBox;

@Route
public class MainView extends VerticalLayout {

    public MainView() {
        add(new Button("Click me", e -> Notification.show("Hello, Spring+Vaadin user!")));
        add(new MainPagePreview());
    }

}

