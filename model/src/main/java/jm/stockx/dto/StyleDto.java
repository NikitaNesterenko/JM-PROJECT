package jm.stockx.dto;

import jm.stockx.entity.Style;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StyleDto {

    private Long id;

    @NotBlank
    private String name;

    public StyleDto(Style style) {
        this.id = style.getId();
        this.name = style.getName();
    }
}
