package jm.stockx;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String storeFile(Long id, String type, MultipartFile file);

    Resource loadFileAsResource(String type, String filename);

    String fileFormat(String filename);
}
