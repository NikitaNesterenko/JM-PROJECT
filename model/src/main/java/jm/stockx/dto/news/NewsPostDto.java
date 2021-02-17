package jm.stockx.dto.news;

import jm.stockx.entity.News;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsPostDto {

    @NotBlank
    private String name;

    private LocalDateTime time;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String text;

    public NewsPostDto(News news) {
        this.name = news.getName();
        this.time = news.getTime();
        this.title = news.getTitle();
        this.description = news.getDescription();
    }
}
