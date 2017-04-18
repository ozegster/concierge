package ba.codecentric.base.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${image.directory.path}")
    private String directoryPath;

    @Override
    public boolean saveImage(InputStream image, String name) {
        Path filePath = Paths.get(directoryPath + name);
        try {
            Files.copy(image, filePath);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
