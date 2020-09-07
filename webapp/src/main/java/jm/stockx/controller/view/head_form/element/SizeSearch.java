package jm.stockx.controller.view.head_form.element;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("item")
@CssImport("./styles/css/sizeSearch.css")
public class SizeSearch extends Div {
    private String size;
    private String text;
    private String urlLeft;
    private String urlRight;

    private Anchor anchorLeft;
    private Anchor anchorRight;

    public SizeSearch(){
        this.size = "size";
        this.text = "text";
        this.urlLeft = "urlLeft";
        this.urlRight = "urlRight";
        anchorLeft = new Anchor(urlLeft, "Size " + size);
        anchorRight = new Anchor(urlRight, "View all " + text);
        initClass();
        add(anchorLeft, anchorRight);
    }

    public SizeSearch(String size, String text, String urlLeft, String urlRight) {
        this.size = size;
        this.text = text;
        this.urlLeft = urlLeft;
        this.urlRight = urlRight;
        anchorLeft = new Anchor(urlLeft, "Size " + size);
        anchorRight = new Anchor(urlRight, "View all " + text);
        initClass();
        add(anchorLeft, anchorRight);

    }

    private void initSizeSearch() {

    }

    private void initClass() {
        anchorLeft.addClassName("anchor_left_style");
        this.addClassName("size_search_style");
    }
}
