package jm.stockx.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CallbackFormDto {

    @Email(message = "Email should be valid")
    private String email;

    @NotNull
    private Long primaryOrderNumber;

    @NotNull
    @NotBlank
    private String type;

    @NotNull
    @NotBlank
    private String subType;

    @NotNull
    @NotBlank
    private String description;
}
