package jm.stockx.api.dao;

import jm.stockx.entity.News;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class NewsDaoImpl extends AbstractDAO<News, Long> implements NewsDAO {

    @Override
    public Optional<News> getByName(String name) {
        try {
            News news = (News) entityManager.createNativeQuery("SELECT * FROM news AS n WHERE n.name = :newsname")
                    .setParameter("newsname", name)
                    .getSingleResult();
            return Optional.of(news);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
