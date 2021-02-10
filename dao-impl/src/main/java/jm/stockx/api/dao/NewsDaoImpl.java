package jm.stockx.api.dao;

import jm.stockx.dto.news.NewsDto;
import jm.stockx.entity.News;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDaoImpl extends AbstractDAO<News, Long> implements NewsDAO {

    @Override
    public NewsDto getNewsDtoByNewsName(String name) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.news.NewsDto(" +
                "n.id," +
                "n.name," +
                "n.title" +
                ") " +
                "FROM News AS n " +
                "WHERE n.name = :newsName", NewsDto.class)
                .setParameter("newsName", name)
                .getSingleResult();
    }

    @Override
    public List<NewsDto> getSixLatestNews() {
        return entityManager.createQuery("" +
                        "SELECT NEW jm.stockx.dto.news.NewsDto(" +
                        "n.title," +
                        "n.time" +
                        ") " +
                        "FROM News AS n " +
                        "ORDER BY n.time DESC",
                NewsDto.class)
                .setMaxResults(6)
                .getResultList();
    }

    @Override
    public NewsDto getNewsDtoByNewsId(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.news.NewsDto(" +
                "n.id," +
                "n.name," +
                "n.title" +
                ") " +
                "FROM News AS n " +
                "WHERE n.id =: id", NewsDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}