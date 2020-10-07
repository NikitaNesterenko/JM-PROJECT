package jm.stockx;

import jm.stockx.dto.NewsDto;
import jm.stockx.entity.News;

import java.util.List;
import java.util.Set;

public interface NewsService {

    Set<News> getAllNews();

    NewsDto get(Long id);

    void create(News news);

    void update(News news);

    void delete(Long id);

    NewsDto getNewsByName(String name);

    boolean isNewsExist(Long id);

}
