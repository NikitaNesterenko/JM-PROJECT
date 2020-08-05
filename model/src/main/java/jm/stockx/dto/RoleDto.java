package jm.stockx.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private Long id;

    @NotBlank
    private String name;
}
