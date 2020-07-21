package jm.stockx.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TelegramUserDTO {
    private Long id;
    private String first_name;
    private String last_name;
    private String username;
    private String photo_url;
    private Long auth_date;
    private String hash;
}
