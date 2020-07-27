package jm.stockx.dto;

import jm.stockx.entity.News;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsPutDto {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    private LocalDateTime time;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String text;

    public NewsPutDto(News news) {
        this.id = news.getId();
        this.name = news.getName();
        this.time = news.getTime();
        this.title = news.getTitle();
        this.description = news.getDescription();
    }

}
