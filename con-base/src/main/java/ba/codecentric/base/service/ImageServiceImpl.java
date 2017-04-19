package ba.codecentric.base.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${image.directory.path}")
    private String directoryPath;

    @Value("${image.timestamp.format}")
    private String timestampFormat;

    /**
     * Saving an image from InputStream on the file system
     * If the directory doesn't exist, it is created
     * Name of the image is a timestamp and it is returned as a string
     *
     * @param image
     * @param name
     * @return
     * @throws IOException
     */
    @Override
    public String saveImage(InputStream image, String name) throws IOException {
        Path path = Paths.get(directoryPath);
        String newFileName;
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            newFileName = getImageName(name);
            Path filePath = Paths.get(directoryPath + newFileName);
            Files.copy(image, filePath);
        } finally {
            image.close();
        }

        return newFileName;
    }

    public String getImageName(String oldName) {
        String[] parts = oldName.split("\\.");
        String timeStamp = new SimpleDateFormat(timestampFormat).format(new Date());
        return timeStamp + "." + parts[parts.length - 1];
    }
}
