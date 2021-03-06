package jm.stockx.dto.realizeCalendar;

import jm.stockx.entity.ItemInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.money.Money;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RealizeCalendarDto {

//    дто {
//        дата релиза,
//        название итема,
//        имейдж урл
//        ловестаск
//        итемайди
//    }

    private Long      itemId;
    private LocalDate realizeDate;
    private String    itemName;
    private String    imageURL;
    private Money     lowestAsk;

    public RealizeCalendarDto(ItemInfo inf) {
        this.itemId = inf.getItem().getId();
        this.realizeDate = inf.getReleaseDate();
        this.itemName = inf.getItem().getName();
        this.imageURL = inf.getItemImageUrl();
        this.lowestAsk = inf.getLowestAsk();
    }


}
