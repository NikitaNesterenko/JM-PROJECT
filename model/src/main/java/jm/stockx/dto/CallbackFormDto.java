package jm.stockx.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CallbackFormDto {
    private String email;
    private Long primaryOrderNumber;
    private String type;
    private String subType;
    private String description;

}
