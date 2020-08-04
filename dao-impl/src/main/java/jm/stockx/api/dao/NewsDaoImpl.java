package jm.stockx.api.dao;

import jm.stockx.dto.NewsDto;
import jm.stockx.entity.News;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class NewsDaoImpl extends AbstractDAO<News, Long> implements NewsDAO {

    @Override
    public Optional<News> getByName(String name) {
        News news = (News) entityManager.createQuery("" +
                "FROM News AS n " +
                "WHERE n.name = :newsName")
                .setParameter("newsName", name)
                .getSingleResult();
        return Optional.of(news);
    }

    @Override
    public NewsDto getNewsDtoById(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.NewsDto(" +
                "n.id, " +
                "n.name," +
                "n.time," +
                "n.title," +
                "n.description," +
                "n.text," +
                "n.image)" +
                "FROM News AS n " +
                "WHERE id =: id", NewsDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
