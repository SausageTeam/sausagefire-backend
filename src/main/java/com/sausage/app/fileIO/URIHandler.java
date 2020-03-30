package com.sausage.app.fileIO;

import com.sausage.app.config.FileStorageProperties;
import com.sausage.app.exception.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class URIHandler {

    private final Path fileStorageLocation;

    @Autowired
    public URIHandler(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String getUri(String path, String fileName){
        String filePath = String.format("/api/downloadFile/%s/%s", path, fileName);
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(filePath)
                .toUriString();
    }

    public String storeFile(int employeeID, MultipartFile file) {
        String fileName = "avatar.jpg";

        try {
            Path targetLocation = this.fileStorageLocation.resolve(employeeID + "/" + fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
        return fileName;
    }

}
