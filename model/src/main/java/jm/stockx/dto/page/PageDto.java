package jm.stockx.dto.page;

import jm.Page;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageDto<T> {

    private Long id;

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

    public PageDto(Integer totalEntitiesCount,
                   Integer currentPage,
                   Integer pageCount,
                   Integer countOnPage,
                   List<T> entities) {
        this.totalEntitiesCount = totalEntitiesCount;
        this.currentPage = currentPage;
        this.pageCount = pageCount;
        this.countOnPage = countOnPage;
        this.entities = entities;
    }
}
