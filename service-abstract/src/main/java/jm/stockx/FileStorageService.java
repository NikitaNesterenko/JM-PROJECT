package jm.stockx;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String storeFile(Long id, MultipartFile file);

    String storeFile(MultipartFile file);

    Resource loadFileAsResource(String filename);

    String fileFormat(String filename);
}
