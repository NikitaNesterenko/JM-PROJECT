package jm.stockx.rest_controller;

import jm.stockx.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/item")
public class FileStorageRestController {
    private FileStorageService fileStorageService;

    @Autowired
    public FileStorageRestController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/img/upload")
    public String uploadItemPicture(@RequestParam("id") Long id, MultipartFile file) {
        return fileStorageService.storeFile(id, file);
    }

    @GetMapping("/img/download")
    public ResponseEntity<Resource> downloadItemPicture(@RequestParam("filename") String filename,
                                                        HttpServletRequest request)  throws Exception{
        Resource resource = fileStorageService.loadFileAsResource(filename);

        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
