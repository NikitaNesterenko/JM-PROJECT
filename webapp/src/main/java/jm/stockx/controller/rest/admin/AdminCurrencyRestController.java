package jm.stockx.controller.rest.admin;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.stockx.BrandService;
import jm.stockx.CurrencyService;
import jm.stockx.entity.Brand;
import jm.stockx.entity.Currency;
import jm.stockx.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/api/admin/Currency")
@Tag(name = "currency", description = "Currency API")
@Slf4j
public class AdminCurrencyRestController {
    private static final Logger logger = LoggerFactory.getLogger(AdminCurrencyRestController.class);

    private final CurrencyService currencyService;

    public AdminCurrencyRestController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    @Operation(
            operationId = "getAllCurrencies",
            summary = "Get all currencies",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(type = "array", implementation = Currency.class)
                            ),
                            description = "OK: currency list received"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: no currencies found")
            })
    public Response<List<Currency>> getAllCurrencies() {
        List<Currency> currencies = currencyService.getAll();
        logger.info("Получен список валют. Всего {} записей.", currencies.size());
        return Response.ok(currencies);
    }

    @GetMapping(value = "/{id}")
    @Operation(
            operationId = "getCurrencyById",
            summary = "Get currency by id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(type = "array", implementation = Currency.class)
                            ),
                            description = "OK: currency received"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: no currency with this currency-id")
            })
    public Response<Currency> getCurrencyById(@PathVariable Long id) {

        if (currencyService.doesItExistEntity(id)) {
            Currency currency = currencyService.get(id);
            logger.info("Получена валюта {} ", currency);
            return Response.ok(currency);
        }
        logger.warn("Валюта с id = {} в базе не найдена", id);
        return Response.error(HttpStatus.BAD_REQUEST, "Currency not found");
    }

    @PostMapping
    @Operation(
            operationId = "createCurrency",
            summary = "Create currency",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Currency.class)
                            ),
                            description = "OK: currency created"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: currency was not created")
            })
    public Response<?> createCurrency(Currency currency) {

        if (currencyService.doesItExistEntity(currency.getId())) {
            logger.warn("Валюта> {} уже существует в базе", currency.getName());
            return Response.error(HttpStatus.BAD_REQUEST, "This currency already exists in database");
        }
        currencyService.create(currency);
        logger.info("Валюта {} успешно создана", currency);
        return Response.ok().build();
    }

    @PutMapping
    @Operation(
            operationId = "updateCurrency",
            summary = "Update currency",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Currency.class)
                            ),
                            description = "OK: currency updated successfully"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: unable to update currency")
            })
    public Response<?> updateCurrency(Currency currency) {
        String currencyName = currency.getName();
        if (currencyService.doesItExistEntity(currency.getId())) {
            currencyService.update(currency);
            logger.info("Валюта {} успешно обновлена", currencyName);
            return Response.ok().build();
        }
        logger.warn("Валюта {} в базе не найдена", currencyName);
        return Response.error(HttpStatus.BAD_REQUEST, "Currency not found");
    }

    @DeleteMapping(value = "/{id}")
    @Operation(
            operationId = "deleteCurrency",
            summary = "Delete currency",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK: currency deleted successfully"),
                    @ApiResponse(responseCode = "400", description = "NOT FOUND: no currency with such id")
            }
    )
    public Response<?> deleteCurrency(@PathVariable Long id) {
        if (currencyService.doesItExistEntity(id)) {
            currencyService.delete(id);
            logger.info("Валюта с id = {} успешно удалёна", id);
            return Response.ok().build();
        }
        logger.warn("Валюта с id = {} в базе не найдена", id);
        return Response.error(HttpStatus.BAD_REQUEST, "Currency not found");
    }
}
