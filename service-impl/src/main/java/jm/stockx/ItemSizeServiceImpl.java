package jm.stockx;

import jm.stockx.api.dao.ShoeSizeDAO;
import jm.stockx.dto.shoeSize.ShoeSizeDto;
import jm.stockx.entity.ItemSize;
import jm.stockx.enums.ItemSizeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ItemSizeServiceImpl implements ItemSizeService {

    private final ShoeSizeDAO shoeSizeDAO;

    @Autowired
    public ItemSizeServiceImpl(ShoeSizeDAO shoeSizeDAO) {
        this.shoeSizeDAO = shoeSizeDAO;
    }


    @Override
    public ItemSize findOneBySizeName(String sizeName) {
        return shoeSizeDAO.findOneBySizeName(sizeName);
    }

    @Override
    public Set<ItemSize> getAll() {
        return shoeSizeDAO.getAll();
    }

    @Override
    public ShoeSizeDto getShoeSizeDtoByShoeSizeId(Long id) {
        return shoeSizeDAO.getShoeSizeDtoByShoeSizeId(id);
    }

    @Override
    public void create(ItemSize itemSize) {
        shoeSizeDAO.add(itemSize);
    }

    @Override
    public void delete(Long id) {
        shoeSizeDAO.deleteById(id);
    }

    @Override
    public void update(ItemSize itemSize) {
        shoeSizeDAO.update(itemSize);
    }

    @Override
    public ShoeSizeDto getShoeSizeDtoByShoeSizeName(String name) {
        return shoeSizeDAO.getShoeSizeDtoByShoeSizeName(name);
    }

    @Override
    public boolean isShoeSizeExist(Long id) {
        return shoeSizeDAO.doesItExistEntity(id);
    }

    @Override
    public List<ShoeSizeDto> getShoeSizeDtoByShoeSizeType(ItemSizeTypes sizeType) {
        List<ShoeSizeDto> list = new ArrayList<>();
        shoeSizeDAO.getShoeSizeDtoByShoeSizeType(sizeType).forEach(x -> list.add(new ShoeSizeDto(x)));
        return list;
    }

}
