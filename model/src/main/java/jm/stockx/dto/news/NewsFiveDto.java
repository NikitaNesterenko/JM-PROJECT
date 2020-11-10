package jm.stockx.dto.news;

import jm.stockx.entity.News;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsFiveDto {

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    public NewsFiveDto(News news) {
        this.title = news.getTitle();
        this.text = news.getText();
    }
}
