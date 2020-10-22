package jm.stockx.dto.support;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SupportQuestionDto {

    private Long id;

    @NotBlank
    private String text;

    @NotNull
    private Long orderNumber;

    @NotNull
    private LocalDateTime dateTime;
}
