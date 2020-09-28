package jm.stockx.controller.user;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import jm.stockx.SellingInfoService;
import jm.stockx.components.templates.ProductTile;
import jm.stockx.dto.ItemTopInfoDto;

import java.util.List;

@Route("itemblock")
public class ItemBlock extends HorizontalLayout {

    private SellingInfoService service;

    public ItemBlock(SellingInfoService service) {
        this.service = service;
        List<ItemTopInfoDto> itemList = service.getItemTopInfoDto(5);

        for (ItemTopInfoDto item : itemList) {
            ProductTile roductTile = new ProductTile(
                    item.getName(),
                    "Lowest ask",
                    item.getLowestAsk().toString(),
                    item.getCount().toString() + " Sold",
                    item.getItemImageUrl(),
                    "");
            add(roductTile);
        }
    }
}
