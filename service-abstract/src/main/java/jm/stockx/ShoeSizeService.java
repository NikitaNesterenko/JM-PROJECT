package jm.stockx;


import jm.stockx.dto.shoeSize.ShoeSizeDto;
import jm.stockx.entity.ShoeSize;
import jm.stockx.enums.ShoeSizeTypes;

import java.util.List;

public interface ShoeSizeService {
    List<ShoeSize> getAll();

    ShoeSizeDto getShoeSizeDtoByShoeSizeId(Long id);

    void create(ShoeSize shoeSize);

    void delete(Long id);

    void update(ShoeSize shoeSize);

    ShoeSizeDto getShoeSizeDtoByShoeSizeName(String name);

    boolean isShoeSizeExist(Long id);

    List<ShoeSizeDto> getShoeSizeDtoByShoeSizeType(ShoeSizeTypes sizeType);
}

