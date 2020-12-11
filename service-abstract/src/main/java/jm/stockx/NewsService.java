package jm.stockx;

import jm.stockx.dto.news.NewsDto;
import jm.stockx.dto.news.NewsLastDto;
import jm.stockx.entity.News;

import java.util.List;

public interface NewsService {

    List<News> getAllNews();

    NewsDto getNewsDtoByNewsId(Long id);

    void create(News news);

    void update(News news);

    void delete(Long id);

    NewsDto getNewsDtoByNewsName(String name);

    boolean isNewsExist(Long id);

    List<NewsLastDto> getLastNews();

}
