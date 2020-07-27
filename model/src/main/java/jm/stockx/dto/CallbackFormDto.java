package jm.stockx.dto;

import lombok.*;

import javax.validation.constraints.Email;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CallbackFormDto {

    @Email
    private String email;

    private Long primaryOrderNumber;

    private String type;

    private String subType;

    private String description;
}
