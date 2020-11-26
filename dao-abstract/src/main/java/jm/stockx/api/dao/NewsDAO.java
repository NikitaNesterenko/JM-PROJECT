package jm.stockx.api.dao;

import jm.stockx.dto.news.NewsDto;
import jm.stockx.dto.news.NewsLastDto;
import jm.stockx.entity.News;

import java.util.List;

public interface NewsDAO extends GenericDao<News, Long> {
    NewsDto getNewsDtoByNewsName(String name);

    NewsDto getNewsDtoByNewsId(Long id);

    List<NewsLastDto> getLastNews();
}
