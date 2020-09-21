package jm.stockx.api.dao;

import jm.stockx.dto.NewsDto;
import jm.stockx.entity.News;
import org.springframework.stereotype.Repository;

@Repository
public class NewsDaoImpl extends AbstractDAO<News, Long> implements NewsDAO {

    @Override
    public NewsDto getNewsDtoByName(String name) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.NewsDto(" +
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
    public NewsDto getNewsDtoById(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.NewsDto(" +
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
