package jm.stockx.dto;

import jm.stockx.entity.Item;
import jm.stockx.entity.User;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto {
    private Long id;

    @NotNull
    private Long itemId;

    @NotNull
    private Long subscriberId;

    private List<User> subscribers;

    private List<Item> items;

    public SubscriptionDto(Long id, List<User> subscribers, List<Item> items) {
        this.id = id;
        this.subscribers = subscribers;
        this.items = items;
    }
}
