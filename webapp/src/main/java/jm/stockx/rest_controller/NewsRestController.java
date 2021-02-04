package jm.stockx.rest_controller;

import jm.stockx.NewsService;
import jm.stockx.dto.news.NewsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/api/news")
public class NewsRestController {

    private final NewsService newsService;

    public NewsRestController(NewsService newsService) {
        this.newsService = newsService;
    }

    /**
     * Получение последних новостей.
     * Используется для заполнения компонента Main Page.
     *
     * @return List<NewsDto>
     */
    @GetMapping(value = "/lastestformainpage")
    public ResponseEntity<List<NewsDto>> getLatestNews() {
        List<NewsDto> newsFound = newsService.getSixLatestNews();
        return ResponseEntity.ok(newsFound);
    }
}