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
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${image.directory.path}")
    private String directoryPath;

    @Value("${image.timestamp.format}")
    private String timestampFormat;

    /**
     * @return new unique image name (randomUUID + timestamp + .extension)
     * @throws IOException
     */
    @Override
    public String saveImage(InputStream image, String imageName) throws IOException {
        Path defaultDirectoryPath = Paths.get(directoryPath);
        String newImageName;
        try {
            if (!Files.exists(defaultDirectoryPath)) {
                Files.createDirectories(defaultDirectoryPath);
            }
            String[] partsOfTheName = imageName.split("\\.");
            newImageName = UUID.randomUUID() + new SimpleDateFormat(timestampFormat).format(new Date()) + "." + partsOfTheName[partsOfTheName.length - 1];
            Path filePath = Paths.get(directoryPath + newImageName);
            Files.copy(image, filePath);
        } finally {
            image.close();
        }

        return newImageName;
    }

}
