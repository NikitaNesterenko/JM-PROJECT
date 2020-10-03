package jm.stockx.controller.rest;

import jm.stockx.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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
    private FileStorageService fileStorageService;

    @Autowired
    public FileStorageRestController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/item/img/upload")
    public String uploadItemPicture(@RequestParam("id") Long id,
                                    @RequestParam("type") String type,
                                    @RequestParam("file") MultipartFile file) {
        return fileStorageService.storeFile(id, type, file);
    }

    @GetMapping("/item/img/download")
    public ResponseEntity<Resource> downloadItemPicture(@RequestParam("filename") String filename,
                                                        @RequestParam("type") String type,
                                                        HttpServletRequest request)  throws Exception{
        Resource resource = fileStorageService.loadFileAsResource(type, filename);

        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
