package jm.stockx.controller.view.head_form;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.UnorderedList;

@CssImport("./styles/css/headMenu.css")
public class HeadMenu extends Div {
    Nav navHeadMenu = new Nav();
    UnorderedList listHeadMenu = new UnorderedList();
    UnorderedList listBrowseMenu = new UnorderedList();
    UnorderedList listAboutMenu = new UnorderedList();
    UnorderedList listSneakersMenu = new UnorderedList();
    UnorderedList listWatchesMenu = new UnorderedList();
    ListItem browse = new ListItem("Browse");
    ListItem news = new ListItem("News");
    ListItem app = new ListItem("App");
    ListItem about = new ListItem("About");
    ListItem help = new ListItem("Help");
    ListItem login = new ListItem("Login");
    ListItem signUp = new ListItem("Sign Up");
    ListItem sell = new ListItem("Sell");
    ListItem sneakers = new ListItem("Sneakers");
    ListItem watches = new ListItem("Watches");
    ListItem adidas = new ListItem("Adidas");
    ListItem nike = new ListItem("Nike");
    ListItem rolex = new ListItem("Rolex");
    ListItem casio = new ListItem("Casio");
    ListItem howItWorks = new ListItem("How it works");
    ListItem press = new ListItem("Press");

    public HeadMenu() {
        initHeadMenu();
        initClassHeadMenu();
        navHeadMenu.add(listHeadMenu);
        add(navHeadMenu);
    }

    private void initHeadMenu() {
       listWatchesMenu.add(casio, rolex);
       listSneakersMenu.add(adidas, nike);
       listBrowseMenu.add(sneakers, watches);
       listAboutMenu.add(howItWorks, press);
       listHeadMenu.add(
               browse,
               news,
               app,
               about,
               help,
               login,
               signUp,
               sell);
    }

    private void initClassHeadMenu() {
        listHeadMenu.addClassNames("none_style", "top_menu");
        listAboutMenu.addClassNames("non_style", "submenu");
        listBrowseMenu.addClassNames("none_style", "submenu");
        listSneakersMenu.addClassNames("none_style", "submenu");
        listWatchesMenu.addClassNames("none_style", "submenu");
        sell.addClassName("sell-class");
    }

}
