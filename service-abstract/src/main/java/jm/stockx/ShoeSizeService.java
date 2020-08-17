package jm.stockx;


import jm.stockx.dto.ShoeSizeDto;
import jm.stockx.entity.ShoeSize;

import java.util.List;

public interface ShoeSizeService {
    List<ShoeSize> getAll();

    ShoeSize get(Long id);

    void create(ShoeSize shoeSize);

    void delete(Long id);

    void update(ShoeSize shoeSize);

    ShoeSize getShoeSizeByName(String name);

    boolean isShoeSizeExist(Long id);

    ShoeSizeDto getShoeSizedDtoById(Long id);
}

