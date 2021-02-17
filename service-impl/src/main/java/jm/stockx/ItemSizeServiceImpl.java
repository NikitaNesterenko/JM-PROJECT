package jm.stockx;

import jm.stockx.api.dao.ItemSizeDAO;
import jm.stockx.dto.ItemSizeDto;
import jm.stockx.dto.shoesize.ShoeSizeDto;
import jm.stockx.entity.ItemSize;
import jm.stockx.enums.ItemSizeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ItemSizeServiceImpl implements ItemSizeService {

    private final ItemSizeDAO itemSizeDAO;

    @Autowired
    public ItemSizeServiceImpl(ItemSizeDAO itemSizeDAO) {
        this.itemSizeDAO = itemSizeDAO;
    }


    public ItemSize getSizeById(Long id){
        return itemSizeDAO.getById(id);
    }

    @Override
    public ItemSizeDto findOneBySizeName(String sizeName) {
        return itemSizeDAO.findOneBySizeName(sizeName);
    }

    @Override
    public Set<ItemSize> getAll() {
        return new HashSet<>(itemSizeDAO.getAll());
    }

    @Override
    public ShoeSizeDto getShoeSizeDtoByShoeSizeId(Long id) {
        return itemSizeDAO.getShoeSizeDtoByShoeSizeId(id);
    }

    @Override
    public void create(ItemSize itemSize) {
        itemSizeDAO.add(itemSize);
    }

    @Override
    public void delete(Long id) {
        itemSizeDAO.deleteById(id);
    }

    @Override
    public void update(ItemSize itemSize) {
        itemSizeDAO.update(itemSize);
    }

    @Override
    public ShoeSizeDto getShoeSizeDtoByShoeSizeName(String name) {
        return itemSizeDAO.getShoeSizeDtoByShoeSizeName(name);
    }

    @Override
    public boolean isShoeSizeExist(Long id) {
        return itemSizeDAO.doesItExistEntity(id);
    }

    @Override
    public List<ShoeSizeDto> getShoeSizeDtoByShoeSizeType(ItemSizeTypes sizeType) {
        List<ShoeSizeDto> list = new ArrayList<>();
        itemSizeDAO.getShoeSizeDtoByShoeSizeType(sizeType).forEach(x -> list.add(new ShoeSizeDto(x)));
        return list;
    }

}
