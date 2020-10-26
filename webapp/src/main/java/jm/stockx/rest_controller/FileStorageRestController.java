package jm.stockx.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jm.stockx.BrandService;
import jm.stockx.FileStorageService;
import jm.stockx.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
public class FileStorageRestController {
    private final FileStorageService fileStorageService;
    private final BrandService brandService;


    @Autowired
    public FileStorageRestController(FileStorageService fileStorageService, BrandService brandService) {
        this.fileStorageService = fileStorageService;
        this.brandService = brandService;
    }

    @PostMapping("/item/img/upload")
    public String uploadItemPicture(@RequestParam("id") Long id, MultipartFile file) {
        return fileStorageService.storeFile(id, file);
    }

    @GetMapping("/item/img/download")
    public ResponseEntity<Resource> downloadItemPicture(@RequestParam("filename") String filename,
                                                        HttpServletRequest request) throws Exception {
        Resource resource = fileStorageService.loadFileAsResource(filename);

        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    @PostMapping("/brand/logo/upload")
    @Operation(
            operationId = "updateBrandLogo",
            summary = "takes MultipartFile logoFileToUpdate and updates BrandLogo by Brand Id ",
            parameters = {
                    @Parameter(name = "brandId", description = "Brand Id", schema = @Schema(implementation = Long.class)),
                    @Parameter(name = "logoFileToUpdate", description = "MultipartFile Brand logo to update",
                            schema = @Schema(implementation = MultipartFile.class))},
            responses = {
                    @ApiResponse(responseCode = "400", description = "Unable to locate BrandDto with Id"),
                    @ApiResponse(responseCode = "200", description = "Brand logo was successfully updated"),
            }
    )
    public Response<?> updateBrandLogo(@RequestParam Long brandId, @RequestParam MultipartFile logoFileToUpdate) {
        if (!brandService.isBrandExist(brandId)) {
            return Response.error(HttpStatus.NOT_FOUND, "Unable to locate BrandDto with Id" + brandId);
        }
        fileStorageService.updateBrandLogo(brandId, logoFileToUpdate);
        return Response.ok(HttpStatus.NO_CONTENT, "Brand logo was successfully updated");
    }
}
