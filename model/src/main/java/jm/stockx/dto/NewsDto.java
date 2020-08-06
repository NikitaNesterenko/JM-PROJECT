package jm.stockx.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private LocalDateTime time;

    @NotBlank
    @NotNull
    private String title;

    @NotBlank
    @NotNull
    private String description;

    @NotBlank
    @NotNull
    private String text;

    private Blob image;
}
