package jm.stockx;

import lombok.SneakyThrows;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    @Value("${upload.path}")
    private String uploadPath;

    private ItemService itemService;

    @Autowired
    public FileStorageServiceImpl(ItemService itemService) {
        this.itemService = itemService;
    }

    private String hashGenerator(String src) {
        return DigestUtils.md5Hex(src);
    }

    @SneakyThrows
    @Override
    public String storeFile(Long id, MultipartFile file) {
        if (file == null) {
            return "Transferred a non-existent file.";
        }

        String fileFormat = fileFormat(file.getOriginalFilename());
        if (!fileFormat.equals(".png")) {
            return "The file must be at .png format.";
        }
        String filePath = uploadPath + "item_" + id + fileFormat;

        if (!new File(uploadPath).exists()) {
            Files.createDirectories(Path.of(uploadPath).toAbsolutePath().normalize());
        }

        file.transferTo(Path.of(filePath));

        itemService.updateItemImageUrl(id, String.valueOf(Path.of(filePath).toAbsolutePath()));

        return Path.of(filePath).toAbsolutePath() + hashGenerator(file.getName());
    }

    @SneakyThrows
    @Override
    public Resource loadFileAsResource(String filename) {
        Path filePath = Path.of(uploadPath + filename).toAbsolutePath();
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists()) {
            return resource;
        }
        return null;
    }

    @Override
    public String fileFormat(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }
}
