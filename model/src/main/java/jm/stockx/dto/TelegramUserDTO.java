package jm.stockx.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TelegramUserDTO {
    private String id;
    private String first_name;
    private String last_name;
    private String username;
    private String photo_url;
    private String auth_date;
    private String hash;
}
