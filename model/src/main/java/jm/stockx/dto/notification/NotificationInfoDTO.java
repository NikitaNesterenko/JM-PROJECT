package jm.stockx.dto.notification;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NotificationInfoDTO {
    String name;
    Long UserId;
    boolean state;
}
