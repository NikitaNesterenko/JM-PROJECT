package jm.stockx.components.news.news_header;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class HeaderRowNewsPage extends HorizontalLayout {
    private Div stockXLogoDiv = new Div();
    private Div blogDiv = new Div();
    private Div searchDiv = new Div();
    private Div searchInputDiv = new Div();
    private Div menuDiv = new Div();
    private Div hideSearchDiv = new Div();
    private Div headerActionsDiv = new Div();
    private Image stockXLogoImg = new Image("news-page-images/StockXLogo.png", "some stockX logo");
    private Anchor blogTitle = new Anchor();
    private Anchor blogDescription = new Anchor();
    private Button searchBtn = new Button();
    private Button hideSearchBtn = new Button();
    private Button menuBtn = new Button();
    private Input searchInput = new Input();

    public HeaderRowNewsPage() {
        initHeaderRowNewsPage();
    }

    private void configureStockXLogo() {
        Anchor home = new Anchor("#");
        home.add(stockXLogoImg);
        stockXLogoDiv.add(home);
    }

    // Header buttons classes configuration
    //            START
    private void configureMenuBtn() {
        Icon menu = new Icon(VaadinIcon.MENU);
        menu.setColor("white");
        menuBtn.getStyle().set("background-color", "black");
        menuBtn.getStyle().set("align-items", "center");
        menuBtn.getStyle().set("justify-content", "center");
        menuBtn.getStyle().set("border-radius", "4px");
        menuBtn.getStyle().set("margin", "0 0 0 10px");
        menuBtn.getStyle().set("height", "60px");
        menuBtn.getStyle().set("width", "60px");
        menuBtn.setIcon(menu);
    }

    private void configureHideSearchBtn() {
        Icon hideSearch = new Icon(VaadinIcon.CLOSE);
        hideSearch.setColor("black");
        hideSearchBtn.getStyle().set("align-items", "center");
        hideSearchBtn.getStyle().set("justify-content", "center");
        hideSearchBtn.getStyle().set("border-radius", "4px");
        hideSearchBtn.getStyle().set("height", "60px");
        hideSearchBtn.getStyle().set("width", "60px");
        hideSearchBtn.setIcon(hideSearch);
    }

    private void configureSearchInput() {
        searchInput.getStyle().set("height", "40px");
        searchInput.getStyle().set("border-radius", "4px 4px 0 0");
        searchInput.getStyle().set("border", "0");
        searchInput.setPlaceholder("Search for articles, series, etc.");
    }

    private void configureSearchBtn() {
        Icon search = new Icon(VaadinIcon.SEARCH);
        search.setColor("black");
        searchBtn.getStyle().set("align-items", "center");
        searchBtn.getStyle().set("justify-content", "center");
        searchBtn.getStyle().set("border-radius", "4px");
        searchBtn.getStyle().set("height", "60px");
        searchBtn.getStyle().set("width", "60px");
        searchBtn.setIcon(search);
    }

    private void configureMenuDiv() {
        configureMenuBtn();
        menuDiv.add(menuBtn);
    }

    private void configureHideSearchDiv() {
        configureMenuDiv();
        configureHideSearchBtn();
        hideSearchDiv.add(hideSearchBtn);
    }

    private void configureSearchInputDiv() {
        configureHideSearchDiv();
        configureSearchInput();
        searchInputDiv.add(searchInput);
    }

    private void configureSearchDiv() {
        configureSearchInputDiv();
        configureSearchBtn();
//        searchDiv.getStyle().set("transition", "300ms");
        searchDiv.add(searchBtn);
    }

    private void configureHeaderActionsDiv() {
        configureSearchDiv();

        headerActionsDiv.getStyle().set("display", "flex");
        headerActionsDiv.getStyle().set("align-items", "center");
        headerActionsDiv.getStyle().set("justify-content", "center");
        headerActionsDiv.add(searchDiv, menuDiv);
    }
    //            END

    // Header links configuration
    //            START
    private void configureBlogTitle() {
        blogTitle.getStyle().set("display", "inline-block");
        blogTitle.getStyle().set("text-decoration", "none");
        blogTitle.getStyle().set("border-bottom", "2px solid #08a05c");
        blogTitle.getStyle().set("margin", "0");
        blogTitle.getStyle().set("color", "black");
        blogTitle.getStyle().set("letter-spacing", "-.85px");
        blogTitle.getStyle().set("font-size", "1.375rem");
        blogTitle.add("The Magazine");
        blogTitle.setHref("#");
    }

    private void configureBlogDescription() {
        blogDescription.getStyle().set("display", "block");
        blogDescription.getStyle().set("font-size", ".875rem");
        blogDescription.getStyle().set("letter-spacing", "-.54px");
        blogDescription.getStyle().set("text-decoration", "none");
        blogDescription.getStyle().set("color", "black");
        blogDescription.getStyle().set("opacity", ".5");
        blogDescription.getStyle().set("margin", "5px 0 0");
        blogDescription.add("News and Editorials from StockX");
        blogDescription.setHref("#");
    }

    private void configureBlogDiv() {
        configureBlogTitle();
        configureBlogDescription();

        blogDiv.getStyle().set("text-align", "center");
        blogDiv.add(blogTitle, blogDescription);
    }
    //            END

    private void initHeaderRowNewsPage() {
        configureStockXLogo();
        configureBlogDiv();
        configureHeaderActionsDiv();
        clickOnBtnConfiguration();

        setPadding(false);
        setSpacing(false);
        setWidthFull();
        setAlignItems(Alignment.CENTER);
        getStyle().set("justify-content", "space-between");
        getStyle().set("align-items", "center");
        getStyle().set("display", "flex");
        getStyle().set("position", "sticky");
        getStyle().set("margin", "0 auto");
        getStyle().set("padding", "25px 0");
        getStyle().set("max-width", "1370px");
        add(stockXLogoDiv, blogDiv, headerActionsDiv);
    }

    //Header buttons click events configuration
    private void clickOnBtnConfiguration() {
        searchBtn.addClickListener((e) -> {
            searchDiv.remove(searchBtn);
            HorizontalLayout searchField = new HorizontalLayout(searchBtn, searchInput, hideSearchBtn);
            searchField.setAlignItems(Alignment.CENTER);
            searchField.setAlignItems(Alignment.CENTER);
            searchDiv.add(searchField);
        });

        hideSearchBtn.addClickListener((e) -> {
            searchDiv.removeAll();
            searchDiv.add(searchBtn);
        });
    }
}
