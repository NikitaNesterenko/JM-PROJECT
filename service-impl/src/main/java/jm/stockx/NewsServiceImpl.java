package jm.stockx;

import jm.stockx.api.dao.NewsDAO;
import jm.stockx.dto.NewsDto;
import jm.stockx.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {


    private final NewsDAO newsDAO;

    @Autowired
    public NewsServiceImpl(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    @Override
    public Set<News> getAllNews() {
        return newsDAO.getAll();
    }

    @Override
    public NewsDto get(Long id) {
        return newsDAO.getNewsDtoById(id);
    }

    @Override
    public void create(News news) {
        newsDAO.add(news);
    }

    @Override
    public void update(News news) {
        newsDAO.update(news);
    }

    @Override
    public void delete(Long id) {
        newsDAO.deleteById(id);
    }

    @Override
    public NewsDto getNewsByName(String name) {
        return newsDAO.getNewsDtoByName(name);
    }

    @Override
    public boolean isNewsExist(Long id) {
        return newsDAO.doesItExistEntity(id);
    }
}
