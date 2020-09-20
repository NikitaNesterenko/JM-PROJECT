package jm.stockx.controller.user;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jm.stockx.components.how_it_works.hiw_body.BodyRowHIWPage;
import jm.stockx.components.how_it_works.hiw_footer.FooterRowHIWPage;
import jm.stockx.components.how_it_works.hiw_header.HeaderRowHIWPage;

@Route("how-it-works")
public class HowItWorksPage extends VerticalLayout {
    private HeaderRowHIWPage header = new HeaderRowHIWPage();
    private BodyRowHIWPage body = new BodyRowHIWPage();
    private FooterRowHIWPage footer = new FooterRowHIWPage();

    public HowItWorksPage() {
        init();
    }

    private void init() {
        setStyle();
        addConfigure();
    }

    private void setStyle() {
        setPadding(false);
        setHeight("90px");
        setMinWidth("100%");
        setDefaultHorizontalComponentAlignment(Alignment.AUTO);
    }

    private void addConfigure() {
        add(header);
        add(body);
        add(footer);
    }
}
