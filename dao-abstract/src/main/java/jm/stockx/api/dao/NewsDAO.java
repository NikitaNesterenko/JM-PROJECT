package jm.stockx.api.dao;

import jm.stockx.dto.NewsDto;
import jm.stockx.entity.News;

public interface NewsDAO extends GenericDao<News, Long> {
    NewsDto getNewsDtoByName(String name);

    NewsDto getNewsDtoById(Long id);
}
