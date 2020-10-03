package jm.stockx;

import jm.stockx.exceptions.FileStorageException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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


    @Override
    public String storeFile(Long id, String type, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return "Transferred a non-existent file.";
        } else {
            String format = fileFormat(file.getOriginalFilename());
            if (!format.equals(".png")) {
                return "The file must be at png format.";
            }
            String filePath;
            if (type.equals("item") || type.equals("news")) {
                filePath = uploadPath + type + "/item_" + id + format;
            } else {
                return "Invalid image type.\nSpecify \"item\" or \"news\".";
            }

            if (!new File(uploadPath + type + "/").exists()) {
                try {
                    Files.createDirectories(Path.of(uploadPath + type + "/").toAbsolutePath().normalize());
                } catch (IOException e) {
                    throw new FileStorageException("This directory already exists");
                }
            }

            try {
                file.transferTo(Path.of(filePath));
            } catch (IOException e) {
                throw new FileStorageException("Couldn't store file " + file.getOriginalFilename() + "\nPlease try again.", e);
            }

            itemService.updateItemImageUrl(id, String.valueOf(Path.of(filePath).toAbsolutePath()));

            return Path.of(filePath).toAbsolutePath() + hashGenerator(file.getName());
        }
    }

    @Override
    public Resource loadFileAsResource(String type, String filename) {
        Path filePath = Path.of(uploadPath + type + "/" + filename).toAbsolutePath();
        Resource resource;

        try {
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new FileStorageException("File not found.", e);
        }

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
