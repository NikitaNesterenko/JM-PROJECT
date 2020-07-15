package jm.stockx.dto;

import jm.stockx.entity.News;
import lombok.*;

import java.sql.Blob;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsPostDto {

    private String name;

    private LocalDateTime time;

    private String title;

    private String description;

    private String text;

    private Blob image;

    public NewsPostDto(News news) {
        this.name = news.getName();
        this.time = news.getTime();
        this.title = news.getTitle();
        this.description = news.getDescription();
        this.image = news.getImage();
    }

}
