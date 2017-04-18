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
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${image.directory.path}")
    private String directoryPath;

    @Override
    public String saveImage(InputStream image, String name) {
        Path path = Paths.get(directoryPath);

        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            String newFileName = getImageName(name);
            Path filePath = Paths.get(directoryPath + newFileName);
            Files.copy(image, filePath);
            return newFileName;
        } catch (IOException e) {
            return new String();
        }
    }

    public String getImageName(String oldName) {
        String[] parts = oldName.split("\\.");
        String timeStamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
        return timeStamp + "." + parts[parts.length - 1];
    }
}
