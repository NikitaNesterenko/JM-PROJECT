package jm.stockx.rest_controller;

import jm.stockx.NewsService;
import jm.stockx.dto.news.NewsLastDto;
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

    @GetMapping("/lastNews")
    public List<NewsLastDto> getLastNewsDto(){
        return newsService.getLastNews();
    }

    @GetMapping("/")
    public String getTest(){
        return "Hello word";
    }

}
