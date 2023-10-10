package com.integrys.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class StorageService {

    @Value("${upload.dir}")
    public String uploadDir = "./files";

    private final Path rootLocation = Paths.get(uploadDir);
    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    public String store(MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            String ext = filename.substring(filename.lastIndexOf("."));
            filename = UUID.randomUUID() + ext;
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename));
            return filename;
        } catch (Exception e) {
            e.printStackTrace();
//            throw new RuntimeException("FAIL!");
        }
        return null;
    }

    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        try {
            if(!Files.exists(rootLocation))
                Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }

    public void delete(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            FileSystemUtils.deleteRecursively(file);
        } catch (Exception e) {
            throw new RuntimeException("FAIL TO DELETE FILE!");
        }
    }
}