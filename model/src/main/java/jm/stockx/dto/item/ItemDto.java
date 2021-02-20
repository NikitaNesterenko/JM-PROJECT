package jm.stockx.dto.item;

import jm.stockx.entity.Item;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto extends Item {

    private Long id;

    @NotBlank
    private String name;

    public ItemDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
    }
}
