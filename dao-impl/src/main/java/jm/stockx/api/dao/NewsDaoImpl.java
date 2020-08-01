package jm.stockx.api.dao;

import jm.stockx.dto.NewsDto;
import jm.stockx.entity.News;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class NewsDaoImpl extends AbstractDAO<News, Long> implements NewsDAO {

    @Override
    public Optional<News> getByName(String name) {
        News news = (News) entityManager.createNativeQuery("SELECT * " +
                "FROM news AS n " +
                "WHERE n.name = :newsname")
                .setParameter("newsname", name)
                .getSingleResult();
        return Optional.of(news);
    }

    @Override
    public NewsDto getNewsDtoById(Long id) {
        String query = "SELECT new jm.stockx.dto.NewsDto(n.id, n.name)" +
                       "FROM Brand AS n " +
                       "WHERE id =: id";
        return entityManager.createQuery(query, NewsDto.class)
                .setParameter("id", id).getSingleResult();
    }
}
