package jm.stockx.controller.view.news;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("news")
public class NewsPage extends VerticalLayout {
    private HeaderRowNewsPage navPanel = new HeaderRowNewsPage();
    private BodyFirstNewsPage body = new BodyFirstNewsPage();

    public NewsPage() {
        setAlignSelf(Alignment.CENTER, body);
        add(navPanel, body);
    }
}
