package ba.codecentric.base.service;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${image.directory.path}")
    private String directoryPath;

    private final static Logger LOG = Logger.getLogger(ImageServiceImpl.class);

    /**
     * @return new unique image name (randomUUID + timestamp + .extension)
     */
    @Override
    public String saveImage(InputStream image, String imageName) throws IOException {

        String newImageName;

        try {
            createImageDirectories();
            newImageName = getUniqueImageName(imageName);
            Path filePath = Paths.get(directoryPath + newImageName);
            Files.copy(image, filePath);
            LOG.info("Image " + imageName + " saved");
        } finally {
            image.close();
        }
        return newImageName;
    }

    private void createImageDirectories() throws IOException {
        Path defaultDirectoryPath = Paths.get(directoryPath);
        if (!Files.exists(defaultDirectoryPath)) {
            Files.createDirectories(defaultDirectoryPath);
            LOG.info("Create " + directoryPath + " folder");
        }
    }

    private String getUniqueImageName(String imageName) {
        return UUID.randomUUID() + getTimestamp() + "." + getImageExtension(imageName);
    }

    private String getImageExtension(String imageName) {
        String[] partsOfTheName = imageName.split("\\.");
        return partsOfTheName[partsOfTheName.length - 1];
    }

    private String getTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    public void deleteImage(String imageName){
        File file = file = new File(directoryPath + imageName);
        file.delete();
    }

    @Override
    public boolean doesImageExist(String imageName){
        File file = new File(directoryPath + imageName);
        return file.exists();
    }

    @Override
    public String encodeImage(String imageName) throws FileNotFoundException, IOException{
        File file = new File(directoryPath + imageName);
        InputStream is = new FileInputStream(file);
        byte byteArray[] = new byte[(int)file.length()];
        is.read(byteArray);
        String imageString = "data:image/png;base64," + Base64.encodeBase64String(byteArray);

        return imageString;
    }
}
