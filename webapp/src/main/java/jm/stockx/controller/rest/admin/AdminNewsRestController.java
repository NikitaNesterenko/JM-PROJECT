package jm.stockx.controller.rest.admin;

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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/api/admin/news")
@Tag(name = "news", description = "News API")
@Slf4j
public class AdminNewsRestController {
    private final NewsService newsService;

    @Autowired
    public AdminNewsRestController(NewsService newsService) {
        this.newsService = newsService;
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
    public Response<?> createNews(@Valid @RequestBody News news) {
        String newsName = news.getName();
        if (newsService.isNewsExist(news.getId())) {
            log.warn("Новость {} уже существует в базе", newsName);
            return Response.error(HttpStatus.BAD_REQUEST, "Error when creating a news");
        }
        newsService.create(news);
        log.info("Новость {} успешно создана", newsName);
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
    public Response<?> updateNews(@Valid @RequestBody News news) {
        String newsName = news.getName();
        if (newsService.isNewsExist(news.getId())) {
            newsService.update(news);
            log.info("новость {} успешно обновлена", newsName);
            return Response.ok().build();
        }
        log.warn("Новость {} в базе не найдена", newsName);
        return Response.error(HttpStatus.BAD_REQUEST, "News not found");
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
    public Response<Boolean> deleteNews(@PathVariable Long id) {
        if (newsService.isNewsExist(id)) {
            newsService.delete(id);
            log.info("Новость с id = {} успешно удалена", id);
            return Response.ok().build();
        }
        log.warn("Новость с id = {} в базе не найдена", id);
        return Response.error(HttpStatus.BAD_REQUEST, "News not found");
    }

    @PostMapping(value = "/addNewsImage")
    public Response<?> addNewsImage(@RequestParam MultipartFile file, @RequestParam Long id) {
        News news = newsService.get(id);
        String fileName = file.getOriginalFilename();
        String uploadRootPath = AdminItemRestController.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        news.setImageUrl(uploadRootPath + news.getName());
        newsService.update(news);
        File uploadRootDir = new File(uploadRootPath + news.getName());

        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        List<File> uploadedFiles = new ArrayList<File>();
        if (fileName != null && fileName.length() > 0) {
            try {
                File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(file.getBytes());
                stream.close();
                uploadedFiles.add(serverFile);
            } catch (Exception e) {
                System.out.println("Error Write file: " + fileName);
                log.warn("Картинка не загружена");
                return Response.error(HttpStatus.BAD_REQUEST, "Image not loaded");
            }
        }
        return Response.ok().build();
    }
}
