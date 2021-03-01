package jm.stockx;

import jm.stockx.api.dao.ItemDAO;
import jm.stockx.api.dao.ItemInfoDAO;
import jm.stockx.dto.item.ItemDtoAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemAdminServiceImpl implements ItemAdminService {

    private final ItemDAO itemDAO;
    private final ItemInfoDAO itemInfoDAO;

    @Autowired
    public ItemAdminServiceImpl(ItemDAO itemDAO, ItemInfoDAO itemInfoDAO) {
        this.itemDAO = itemDAO;
        this.itemInfoDAO = itemInfoDAO;
    }

//    TODO done
    @Override
    public void addAdminItemInfo(ItemDtoAdmin itemDtoAdmin) {
        Long itemId = itemDAO.addItemByItemName(itemDtoAdmin.getName());

        itemInfoDAO.addItemInfo(itemId, itemDtoAdmin);
    }

    @Override
    public void addAdminListItemInfo(List<ItemDtoAdmin> listItemAdminDto) {
        listItemAdminDto.forEach(this::addAdminItemInfo);
    }

}
