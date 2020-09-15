package jm.stockx.controller.view.head_form.element;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;

@Route("soc")
@CssImport("./styles/css/socialBlock.css")
public class SocialBlock extends Div {
    UnorderedList listSocial = new UnorderedList();
    ListItem twit = new ListItem(new Icon(VaadinIcon.TWITTER));
    ListItem facebook = new ListItem(new Icon(VaadinIcon.FACEBOOK));
    ListItem pinterest = new ListItem(new Image("images/iconPinterest.png", "Smth."));
    ListItem email = new ListItem(new Icon(VaadinIcon.ENVELOPE));

    public SocialBlock() {
        initSocialBlock();
        initClass();
        add(listSocial);
    }

    private void initSocialBlock() {
        listSocial.add(twit, facebook, pinterest, email);
    }

    private void initClass() {
        this.addClassName("social_block_style");
        twit.addClassName("twit_style");
        facebook.addClassName("facebook_style");

    }

}
