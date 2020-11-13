package jm.stockx;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface FileStorageService {
    String storeFile(Long id, MultipartFile file);

    Resource loadFileAsResource(String filename);

    String fileFormat(String filename);

    Path uploadImage(MultipartFile logoFileToUpdate, String additionalPath);
}
