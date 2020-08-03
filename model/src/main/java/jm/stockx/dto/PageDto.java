package jm.stockx.dto;

import jm.Page;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageDto<T> {

    @Positive
    @NotNull
    private Integer totalEntitiesCount;

    @Positive
    @NotNull
    private Integer currentPage;

    @Positive
    @NotNull
    private Integer pageCount;

    @Positive
    @NotNull
    private Integer countOnPage;

    @NotNull
    private List<T> entities;

    public PageDto(Page<T> page) {
        this.pageCount = Math.toIntExact(page.getCount());
        this.entities = page.getEntities();
        totalEntitiesCount = entities.size();
        currentPage = 0;
    }
}
