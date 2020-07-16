package jm.stockx.controller.rest.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.stockx.NewsService;
import jm.stockx.entity.News;
import jm.stockx.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/api/user/news")
@Tag(name = "news", description = "News API")
@Slf4j
public class UserNewsRestController {
    private final NewsService newsService;

    @Autowired
    public UserNewsRestController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    @Operation(
            operationId = "getAllNews",
            summary = "Get all news",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(type = "array", implementation = News.class)
                            ),
                            description = "OK: news list received"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: no news found")
            })
    public Response<List<News>> getAllNews() {
        List<News> news = newsService.getAllNews();
        log.info("Получен список новостей. Всего {} записей.", news.size());
        return Response.ok(news);
    }

    @GetMapping(value = "/{id}")
    @Operation(
            operationId = "getNewsById",
            summary = "Get news by id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(type = "array", implementation = News.class)
                            ),
                            description = "OK: got news"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: no news with this news id")
            })
    public Response<News> getNewsById(@PathVariable("id") Long id) {
        News news = newsService.get(id);
        if (news == null) {
            log.warn("Новость с id = {} в базе не найден", id);
            return Response.error(HttpStatus.BAD_REQUEST, "News not found");
        }
        log.info("Получена новость {} ", news);
        return Response.ok(news);
    }
}
