package jm.stockx.dto.itemInfo;

import jm.stockx.entity.ItemInfo;
import jm.stockx.entity.ShoeSize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.joda.money.Money;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemInfoDto {

    private Long id;

    @Positive(message = "Цена должна быть положительной")
    @NotNull
    private Money price;

    @Positive(message = "Цена должна быть положительной")
    @NotNull
    private Money lowestAsk;

    @Positive(message = "Ставка должна быть положительной")
    @NotNull
    private Money highestBid;

    private Set<Double> sizes;

    private Long ItemId;


    public ItemInfoDto(ItemInfo itemInfo) {
        this.id = itemInfo.getId();
        this.price = itemInfo.getPrice();
        this.lowestAsk = itemInfo.getLowestAsk();
        this.highestBid = itemInfo.getHighestBid();
        this.sizes = convertShoeSize(itemInfo.getSizes());
        this.ItemId = itemInfo.getItem().getId();
    }

    private Set<Double> convertShoeSize(Set<ShoeSize> shoeSizes){
        Set<Double> sizes = new HashSet<>();
        shoeSizes.forEach(e -> sizes.add(e.getSize()));
        return sizes;
    }
}
