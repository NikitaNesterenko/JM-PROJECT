package jm.stockx.dto.news;

import jm.stockx.entity.News;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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

    public NewsDto(Long id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }

    public NewsDto(@NotBlank String name, @NotNull LocalDateTime time) {
        this.name = name;
        this.time = time;
    }
}