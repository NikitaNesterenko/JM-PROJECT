package jm.stockx.dto;

import jm.stockx.enums.ItemSizeTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemSizeDto {

    private Long id;

    private String size;

    @Enumerated(EnumType.STRING)
    private ItemSizeTypes sizeTypes;

    public ItemSizeDto(String size, ItemSizeTypes sizeTypes) {
        this.size = size;
        this.sizeTypes = sizeTypes;
    }

    @Override
    public String toString() {
        return size + " " + sizeTypes;
    }
}
