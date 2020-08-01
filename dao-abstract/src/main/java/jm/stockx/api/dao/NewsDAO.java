package jm.stockx.api.dao;

import jm.stockx.entity.News;

import java.util.Optional;

public interface NewsDAO extends GenericDao<News, Long> {
    Optional<News> getByName(String name);
    boolean doesItExistEntity(Long id);
}
