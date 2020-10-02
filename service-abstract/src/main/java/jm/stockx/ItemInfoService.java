package jm.stockx;

import jm.stockx.dto.ItemInfoGetDto;
import jm.stockx.entity.ItemInfo;

import java.util.List;

public interface ItemInfoService {

    List<ItemInfo> getAllNews();

    ItemInfo get(Long id);

    void create(ItemInfo itemInfo);

    void update(ItemInfo itemInfo);

    void delete(Long id);

    ItemInfo getItemInfoByItemId(Long ItemId);

    List<ItemInfoGetDto> getListAndOrderByCash(Integer cash);
}
