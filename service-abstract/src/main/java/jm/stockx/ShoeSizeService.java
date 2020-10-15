package jm.stockx;


import jm.stockx.dto.ShoeSizeDto;
import jm.stockx.entity.ShoeSize;
import jm.stockx.enums.ShoeSizeTypes;

import java.util.List;
import java.util.Set;

public interface ShoeSizeService {
    List<ShoeSize> getAll();

    ShoeSizeDto get(Long id);

    ShoeSize findOneBySize(Double size);

    void create(ShoeSize shoeSize);

    void delete(Long id);

    void update(ShoeSize shoeSize);

    ShoeSizeDto getShoeSizeByName(String name);

    boolean isShoeSizeExist(Long id);

    ShoeSizeDto getShoeSizedDtoById(Long id);

    List<ShoeSizeDto> getShoeSizeDtoBySizeType(ShoeSizeTypes sizeType);
}

