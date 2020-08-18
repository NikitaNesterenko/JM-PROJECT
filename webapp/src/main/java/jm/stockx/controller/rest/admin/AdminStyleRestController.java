package jm.stockx.controller.rest.admin;

import jm.stockx.StyleService;
import jm.stockx.dto.StyleDto;
import jm.stockx.dto.StylePutDto;
import jm.stockx.entity.Style;
import jm.stockx.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedList;
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
    public Response<List<StyleDto>> getAllStyles() {
        List<StyleDto> styleDtos = new LinkedList<>();
        List<Style> styles = styleService.getAll();
        for (Style style : styles
        ) {
            StyleDto styleDto = new StyleDto(style);
            styleDtos.add(styleDto);
        }
        log.info("Получен список стилей. Всего {} записей.", styles.size());
        return Response.ok(styleDtos);
    }

    @GetMapping(value = "/{id}")
    public Response<StyleDto> getStyleById(@PathVariable Long id) {
        if (styleService.isStyleExist(id)) {
            StyleDto styleDto = styleService.getStyleDtoById(id);
            log.info("Получен стиль {} ", styleDto);
            return Response.ok(styleDto);
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
    public Response<?> updateStyle(@Valid @RequestBody StylePutDto stylePutDto) {
        if (styleService.isStyleExist(stylePutDto.getId())) {
            Style styleUpdate = new Style(stylePutDto.getId(), stylePutDto.getName());
            styleService.update(styleUpdate);
            log.info("Стиль с id = {} успешно обновлен", stylePutDto.getId());
            return Response.ok(stylePutDto);
        }
        log.warn("Стиль с id = {} в базе не найден", stylePutDto.getId());
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
