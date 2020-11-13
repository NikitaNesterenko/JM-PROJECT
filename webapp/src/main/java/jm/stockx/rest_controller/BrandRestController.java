package jm.stockx.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jm.stockx.BrandService;
import jm.stockx.FileStorageService;
import jm.stockx.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;

@RestController
@RequestMapping(value = "")
public class BrandRestController {

    private final BrandService brandService;
    private final FileStorageService fileStorageService;
    private final String additionalPathForBrands = "brands" + File.separator;

    @Autowired
    public BrandRestController(BrandService brandService, FileStorageService fileStorageService) {
        this.brandService = brandService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload")
    @Operation(
            operationId = "updateBrandLogo",
            summary = "takes MultipartFile logoFileToUpdate and updates BrandLogo by Brand Id ",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "brandId", description = "Brand Id", schema = @Schema(implementation = Long.class)),
                    @Parameter(name = "logoFileToUpdate", description = "MultipartFile Brand logo to update",
                            schema = @Schema(implementation = MultipartFile.class))},
            responses = {
                    @ApiResponse(responseCode = "400", description = "Unable to locate BrandDto with Id"),
                    @ApiResponse(responseCode = "400", description = "File name is not present"),
                    @ApiResponse(responseCode = "200", description = "Brand logo was successfully updated"),
            }
    )
    public Response<?> updateBrandLogo(@RequestParam Long brandId, @RequestParam MultipartFile logoFileToUpdate) {
        if (!brandService.isBrandExist(brandId)) {
            return Response.error(HttpStatus.NOT_FOUND, "Unable to locate BrandDto with Id" + brandId);
        }
        Path fileNameAndPath = fileStorageService.uploadImage(logoFileToUpdate, additionalPathForBrands);
        String fileNameForDb = fileNameAndPath.getFileName().toString().substring(8);

        brandService.updateBrandLogo(brandId, fileNameForDb);
        return Response.ok(HttpStatus.OK, "Image was successfully updated");
    }
}
