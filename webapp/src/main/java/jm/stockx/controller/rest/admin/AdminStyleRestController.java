package jm.stockx.controller.rest.admin;

import jm.stockx.StyleService;
import jm.stockx.entity.Style;
import jm.stockx.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/api/admin/styles")
@Slf4j
public class AdminStyleRestController {
    private final StyleService styleService;

    @Autowired
    public AdminStyleRestController(StyleService styleService) {
        this.styleService = styleService;
    }

    @GetMapping
    public Response<List<Style>> getAllStyles() {
        List<Style> styles = styleService.getAll();
        log.info("Получен список стилей. Всего {} записей.", styles.size());
        return Response.ok(styles);
    }

    @GetMapping(value = "/{id}")
    public Response<Style> getStyleById(@PathVariable Long id) {
        if (styleService.isStyleExist(id)) {
            Style style = styleService.get(id);
            log.info("Получен стиль {} ", style);
            return Response.ok(style);
        }
        log.warn("Стиль с id = {} в базе не найден", id);
        return Response.error(HttpStatus.BAD_REQUEST, "Style not found");
    }

    @PostMapping
    public Response<?> createStyle(@Valid @RequestBody Style style) {
        String styleName = style.getName();
        if (styleService.isStyleExist(style.getId())) {
            log.warn("Стиль с именем {} уже существует в базе", styleName);
            return Response.error(HttpStatus.BAD_REQUEST, "This style already exists in database");
        }
        styleService.create(style);
        log.info("Стиль {} успешно создан", styleName);
        return Response.ok(style);
    }

    @PutMapping
    public Response<?> updateStyle(@Valid @RequestBody Style style) {
        if (styleService.isStyleExist(style.getId())) {
            styleService.update(style);
            log.info("Стиль с id = {} успешно обновлен", style.getId());
            return Response.ok(style);
        }
        log.warn("Стиль с id = {} в базе не найден", style.getId());
        return Response.error(HttpStatus.BAD_REQUEST, "Style not found");
    }

    @DeleteMapping(value = "/{id}")
    public Response<?> deleteStyle(@PathVariable Long id) {
        if (styleService.isStyleExist(id)) {
            styleService.delete(id);
            log.info("Стиль с id = {} успешно удален", id);
            return Response.ok().build();
        }
        log.warn("Стиль с id = {} в базе не найден", id);
        return Response.error(HttpStatus.BAD_REQUEST, "Style not found");
    }
}
