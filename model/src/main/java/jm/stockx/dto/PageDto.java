package jm.stockx.dto;

import jm.Page;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageDto<T> {

    private Integer totalEntitiesCount;

    private Integer currentPage;

    private Integer pageCount;

    private Integer countOnPage;

    private List<T> entities;

    public PageDto(Page<T> page) {
        this.pageCount = Math.toIntExact(page.getCount());
        this.entities = page.getEntities();
        totalEntitiesCount = entities.size();
        currentPage = 0;
    }
}
