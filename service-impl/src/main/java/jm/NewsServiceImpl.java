package jm;

import jm.api.dao.NewsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {


    private final NewsDAO newsDAO;

    @Autowired
    public NewsServiceImpl(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    @Override
    public List<News> getAllNews() {
        return newsDAO.getAll();
    }

    @Override
    public News get(Long id) {
        return newsDAO.getById(id);
    }

    @Override
    public void create(News news) {
        newsDAO.add(news);
    }

    @Override
    public void update(News news) {
        newsDAO.merge(news);
    }

    @Override
    public void delete(Long id) {
        newsDAO.deleteById(id);
    }

    @Override
    public News getNewsByName(String name) {
        return newsDAO.getNewsByName(name).get();
    }
}
