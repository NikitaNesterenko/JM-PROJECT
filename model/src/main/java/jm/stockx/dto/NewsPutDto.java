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

    @NotNull                        // так как put для изменения, id должен быть
    private Long id;

    @NotBlank                       // не должно быть null, пустым или состоять из одних лишь пробельных символов
    private String name;

    private LocalDateTime time;

    @NotBlank                       // не должно быть null, пустым или состоять из одних лишь пробельных символов
    private String title;

    @NotBlank                       // не должно быть null, пустым или состоять из одних лишь пробельных символов
    private String description;

    @NotBlank                       // не должно быть null, пустым или состоять из одних лишь пробельных символов
    private String text;

    public NewsPutDto(News news) {
        this.id = news.getId();
        this.name = news.getName();
        this.time = news.getTime();
        this.title = news.getTitle();
        this.description = news.getDescription();
    }

}
