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
import java.nio.file.Paths;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    @Value("${upload.path}")
    private String uploadPath;

    private static final String uploadDirectory =
            System.getProperty("user.dir")
                    + File.separator
                    + "uploads"
                    + File.separator;

    private ItemInfoService itemInfoService;

    @Autowired
    public FileStorageServiceImpl(ItemService itemService) {
        this.itemInfoService = itemInfoService;
    }

    private String hashGenerator(String src) {
        return DigestUtils.md5Hex(src);
    }


    @Override
    public String storeFile(Long id, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return "Transferred a non-existent file.";
        } else {
            String fileFormat = fileFormat(file.getOriginalFilename());
            if (!fileFormat.equals(".png")) {
                return "The file must be at .png format.";
            }
            String filePath = uploadPath + "item_" + id + fileFormat;

            if (!new File(uploadPath).exists()) {
                try {
                    Files.createDirectories(Path.of(uploadPath).toAbsolutePath().normalize());
                } catch (IOException e) {
                    throw new FileStorageException("This directory already exists");
                }
            }

            try {
                file.transferTo(Path.of(filePath));
            } catch (IOException e) {
                throw new FileStorageException("Couldn't store file " + file.getName() + "\nPlease try again.", e);
            }

            itemInfoService.updateItemImageUrl(id, String.valueOf(Path.of(filePath).toAbsolutePath()));

            return Path.of(filePath).toAbsolutePath() + hashGenerator(file.getName());
        }
    }

    /**
     * Receives brand logo file from FileStorageController and stores it in a local project directory
     * if storage successful, updates brandLogoUrl in Brand entity
     *
     * @param logoFileToUpdate logo to update
     * @throws FileStorageException if failed to store file
     * @throws FileStorageException if passed MultipartFile is Null
     * @return String of original file name
     */
    @Override
    public String uploadImage(MultipartFile logoFileToUpdate, String additionalPath) {
        if (!logoFileToUpdate.isEmpty()) {
            Path fileNameAndPath = Paths.get(uploadDirectory + additionalPath, logoFileToUpdate.getOriginalFilename());
            try {
                byte[] fileToBytes = logoFileToUpdate.getBytes();
                Files.write(fileNameAndPath, fileToBytes);

                return logoFileToUpdate.getOriginalFilename();
            } catch (IOException exception) {
                throw new FileStorageException("Failed to store file: " + exception.getMessage());
            }
        } else {
            throw new FileStorageException("Uploaded file is Empty");
        }
    }


    @Override
    public Resource loadFileAsResource(String filename) {
        Path filePath = Path.of(uploadPath + filename).toAbsolutePath();
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
