package jm.stockx.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {

    @NotNull
    private Long id;
    private String name;
    private LocalDateTime time;
    private String title;
    private String description;
    private String text;
    private Blob image;
}
