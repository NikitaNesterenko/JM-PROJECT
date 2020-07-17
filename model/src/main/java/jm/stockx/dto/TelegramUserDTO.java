package jm.stockx.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TelegramUserDTO {
    private long id;
    private String first_name;
    private String last_name;
    private String username;
    private String photo_url;
    private int auth_date;
    private int hash;
}
