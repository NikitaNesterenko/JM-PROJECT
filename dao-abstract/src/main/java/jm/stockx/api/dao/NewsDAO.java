package jm.stockx.api.dao;

import jm.stockx.dto.news.NewsDto;
import jm.stockx.entity.News;

public interface NewsDAO extends GenericDao<News, Long> {
    NewsDto getNewsDtoByNewsName(String name);

    NewsDto getNewsDtoByNewsId(Long id);
}
