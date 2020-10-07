package jm.stockx.controller;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import jm.stockx.ItemInfoService;
import jm.stockx.ItemService;
import jm.stockx.entity.Item;

@Route("search")
public class SearchController extends VerticalLayout {
    private ItemService itemService;
    private ItemInfoService itemInfoService;
    private Grid<Item> grid = new Grid<>(Item.class);
    private TextField field = new TextField();
    private Label label = new Label();

    public SearchController(ItemService itemService, ItemInfoService itemInfoService) {
        this.itemService = itemService;
        this.itemInfoService = itemInfoService;
        add(field);
        add(label);
        add(grid);

        field.setClearButtonVisible(true);
        field.setValueChangeMode(ValueChangeMode.EAGER);
        field.addValueChangeListener(e -> {

            updateList();
        });
    }

    private void updateList() {
        grid.setItems(itemService.searchItems(field.getValue()));
        label.setText(String.valueOf(itemService.getMap(field.getValue())));
    }
}
