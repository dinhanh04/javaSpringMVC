package com.example.demo.service;

import com.example.demo.domain.Image;
import com.example.demo.repository.ImageRepository;
import jakarta.servlet.ServletContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private String location = "upload-dir";

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }


    public Image handleSaveImage(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return null;
            }
            Path rootLocation = Paths.get(location);
            if (Files.notExists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }

            String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            String fileName = UUID.randomUUID().toString() + fileType;

            Path destinationFile = rootLocation.resolve(
                            Paths.get(fileName))
                    .normalize().toAbsolutePath();

            if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
                return null;
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }

            Image image = new Image();
            image.setName(file.getName());
            image.setPath(destinationFile.toString());
            image = imageRepository.save(image);

            String url = "http://localhost:8080/image/";
            image.setUrl(url + image.getId());
            image = imageRepository.save(image);
            return image;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Image findById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }





}
