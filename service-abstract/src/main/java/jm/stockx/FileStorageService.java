package jm.stockx;

import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public interface FileStorageService {
    String storeFile(Long id, MultipartFile file);

    Resource loadFileAsResource(String filename);

    String fileFormat(String filename);

    void updateBrandLogo(Long brandId, MultipartFile logoFileToUpdate);


}
