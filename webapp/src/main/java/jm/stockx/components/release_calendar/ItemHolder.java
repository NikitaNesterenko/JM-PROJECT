package jm.stockx.components.release_calendar;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.money.Money;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ItemHolder {
    private String itemName;
    private String itemCondition;
    private String itemImgUrl;
    private Money itemLowestAsk;
    private LocalDate itemReleaseDate;

    public ItemHolder(String name,
                      String condition,
                      String imgUrl,
                      Money lowestAsk,
                      LocalDate releaseDate) {
        initItemData(name, condition, imgUrl, lowestAsk, releaseDate);
    }


    private void initItemData(String name,
                              String condition,
                              String imgUrl,
                              Money lowestAsk,
                              LocalDate releaseDate) {
        itemName = name;
        itemCondition = condition;
        itemImgUrl = imgUrl;
        itemLowestAsk = lowestAsk;
        itemReleaseDate = releaseDate;
    }

}
