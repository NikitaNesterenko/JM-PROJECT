package jm.stockx.dto.paymentInfo;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInfoDto {
    private Long id;

    @NotBlank
    @NotNull
    private String cardNumber;

    @NotBlank
    @NotNull
    private String cardExpiresDate;

    @NotBlank
    @NotNull
    private String cvv;

    @NotBlank
    @NotNull
    private String firstName;

    @NotBlank
    @NotNull
    private String lastName;

    @NotBlank
    @NotNull
    private String country;

    @NotBlank
    @NotNull
    private String address;

    @NotBlank
    @NotNull
    private String city;

    @NotBlank
    @NotNull
    private String state;

    @NotBlank
    @NotNull
    private String zipCode;

    @NotBlank
    @NotNull
    private String phoneNumber;
}
