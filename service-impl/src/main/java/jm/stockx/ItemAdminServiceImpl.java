package jm.stockx;

import jm.stockx.dto.item.ItemDtoAdmin;
import jm.stockx.entity.Item;
import jm.stockx.entity.ItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemAdminServiceImpl implements ItemAdminService{

    private final ItemInfoService itemInfoService;
    private final ItemService itemService;

    @Autowired
    public ItemAdminServiceImpl(ItemInfoService itemInfoService, ItemService itemService) {
        this.itemInfoService = itemInfoService;
        this.itemService = itemService;
    }

    @Override
    public void addAdminItemInfo(ItemDtoAdmin itemDtoAdmin) {
        Item item = new Item(itemDtoAdmin.getName());
        itemService.create(item);
        ItemInfo itemInfo = new ItemInfo(itemDtoAdmin);
        itemInfoService.create(itemInfo);
    }
}
