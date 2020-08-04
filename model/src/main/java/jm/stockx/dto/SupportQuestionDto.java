package jm.stockx.dto;

import lombok.*;

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
