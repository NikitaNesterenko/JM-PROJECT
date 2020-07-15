package jm.stockx.api.dao;

import jm.stockx.entity.News;

import java.util.List;
import java.util.Optional;

public interface NewsDAO {

    List<News> getAll();

    News getById(Long id);

    void add(News news);

    void deleteById(Long id);

    News merge(News news);

    Optional<News> getNewsByName(String name);
}
