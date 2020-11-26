package jm.stockx;

import jm.stockx.api.dao.NewsDAO;
import jm.stockx.dto.news.NewsDto;
import jm.stockx.dto.news.NewsLastDto;
import jm.stockx.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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
    public List<NewsLastDto> getLastNews() {
        return newsDAO.getLastNews();
    }

    @Override
    public NewsDto getNewsDtoByNewsId(Long id) {
        return newsDAO.getNewsDtoByNewsId(id);
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
    public NewsDto getNewsDtoByNewsName(String name) {
        return newsDAO.getNewsDtoByNewsName(name);
    }

    @Override
    public boolean isNewsExist(Long id) {
        return newsDAO.doesItExistEntity(id);
    }
}
