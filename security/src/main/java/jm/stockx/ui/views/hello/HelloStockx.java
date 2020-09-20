package jm.stockx.ui.views.hello;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jm.stockx.ui.MainLayout;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Hello")
public class HelloStockx extends VerticalLayout {

    public HelloStockx() {
        addClassName("hello-view");
        setSizeFull();

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        add(new H1("Hello, Stockx"));
    }
}
