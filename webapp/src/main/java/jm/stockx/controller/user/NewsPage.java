package jm.stockx.controller.user;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jm.stockx.components.news.news_header.HeaderRowNewsPage;
import jm.stockx.components.news.news_body.BodyFirstNewsPage;

@Route("news")
public class NewsPage extends VerticalLayout {
    private HeaderRowNewsPage navPanel = new HeaderRowNewsPage();
    private BodyFirstNewsPage body = new BodyFirstNewsPage();

    public NewsPage() {
        setAlignSelf(Alignment.CENTER, body);
        add(navPanel, body);
    }
}
