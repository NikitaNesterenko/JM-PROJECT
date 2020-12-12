package jm.stockx;

import jm.stockx.dto.item.ItemDtoAdmin;
import jm.stockx.entity.Item;
import jm.stockx.entity.ItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemAdminServiceImpl implements ItemAdminService {

    private final ItemInfoService itemInfoService;
    private final ItemService itemService;

    @Autowired
    public ItemAdminServiceImpl(ItemInfoService itemInfoService, ItemService itemService) {
        this.itemInfoService = itemInfoService;
        this.itemService = itemService;
    }

    @Override
    public void addAdminItemInfo(ItemDtoAdmin itemDtoAdmin) throws ItemAdminDtoException {
        if (!itemDtoAdmin.containsNull()) {
            throw new ItemAdminDtoException();
        } else {
            Item item = new Item(itemDtoAdmin.getName());
            itemService.create(item);
            ItemInfo itemInfo = new ItemInfo();
            itemInfo.setPrice(itemDtoAdmin.getPrice());
            itemInfo.setReleaseDate(itemDtoAdmin.getDateRelease());
            itemInfo.setItem(item);
            itemInfo.setItemImageUrl(itemDtoAdmin.getImageUrl());
            itemInfoService.create(itemInfo);
        }
    }

    @Override
    public void addAdminListItemInfo(List<ItemDtoAdmin> listItemAdminDto) {
        listItemAdminDto.forEach(list -> {
            try {
                addAdminItemInfo(list);
            } catch (ItemAdminDtoException e) {
                e.printStackTrace();
            }
        });

    }


}
