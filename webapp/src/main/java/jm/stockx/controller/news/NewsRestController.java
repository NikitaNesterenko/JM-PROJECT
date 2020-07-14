package jm.stockx.controller.news;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.stockx.entity.News;
import jm.stockx.NewsService;
import jm.stockx.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/api/news")
@Tag(name = "news", description = "News API")
public class NewsRestController {
    private static final Logger logger = LoggerFactory.getLogger(NewsRestController.class);

    private final NewsService newsService;

    @Autowired
    public NewsRestController(NewsService newsService) {
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
        logger.info("Получен список новостей. Всего {} записей.", news.size());
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
            logger.warn("Новость с id = {} в базе не найден", id);
            return Response.error(HttpStatus.BAD_REQUEST, "News not found");
        }
        logger.info("Получена новость {} ", news);
        return Response.ok(news);
    }

    @PostMapping(value = "/createNews")
    @Operation(
            operationId = "createNews",
            summary = "Create news",
            responses = {
                    @ApiResponse(
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = News.class)
                            )
                    ),
                    @ApiResponse(responseCode = "200", description = "OK: news created"),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: news not created")
            })
    public Response<?> createNews(News news) {
        String newsName = news.getName();
        if (newsService.getNewsByName(newsName) != null) {
            logger.warn("Новость {} уже существует в базе", newsName);
            return Response.error(HttpStatus.BAD_REQUEST, "Error when creating a news");
        }
        newsService.create(news);
        logger.info("Новость {} успешно создана", newsName);
        return Response.ok(HttpStatus.OK, "News created successfully");
    }

    @PutMapping(value = "/updateNews")
    @Operation(
            operationId = "updateNews",
            summary = "Update news",
            responses = {
                    @ApiResponse(
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = News.class)
                            )
                    ),
                    @ApiResponse(responseCode = "200", description = "OK: news updated successfully"),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: unable to update news")
            })
    public Response<?> updateNews(News news) {
        String newsName = news.getName();
        if (newsService.getNewsByName(news.getName()) == null) {
            logger.warn("Новость {} в базе не найдена", newsName);
            return Response.error(HttpStatus.BAD_REQUEST, "News not found");
        }
        newsService.update(news);
        logger.info("новость {} успешно обновлена", newsName);
        return Response.ok().build();
    }

    @DeleteMapping(value = "/deleteNews")
    @Operation(
            operationId = "deleteNews",
            summary = "Delete news",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK: news deleted successfully"),
                    @ApiResponse(responseCode = "400", description = "NOT FOUND: no news with such id")
            }
    )
    public Response<Boolean> deleteNews(Long id) {
        if (newsService.get(id) == null) {
            logger.warn("Новость с id = {} в базе не найдена", id);
            return Response.error(HttpStatus.BAD_REQUEST, "News not found");
        }
        newsService.delete(id);
        logger.info("Новость с id = {} успешно удалена", id);
        return Response.ok().build();
    }
}
