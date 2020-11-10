package jm.stockx.rest_controller;

import jm.stockx.NewsService;
import jm.stockx.dto.news.NewsFiveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainTestController {

    private final NewsService newsService;

    @Autowired
    public MainTestController(NewsService newsService){
        this.newsService = newsService;
    }

    @GetMapping("/fiveNews")
    public List<NewsFiveDto> getNewsFiveDto(){
        return newsService.getFiveNews();
    }

}
