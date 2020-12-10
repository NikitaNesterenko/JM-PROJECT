package jm.stockx;

import jm.stockx.dto.item.ItemDtoAdmin;

import java.util.List;

public interface ItemAdminService {
    void addAdminItemInfo (ItemDtoAdmin itemDtoAdmin) throws ItemAdminDtoException;
    void addAdminListItemInfo (List<ItemDtoAdmin> listItemAdminDto) throws ItemAdminDtoException;
}
