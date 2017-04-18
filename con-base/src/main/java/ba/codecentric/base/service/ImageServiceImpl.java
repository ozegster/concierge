package ba.codecentric.base.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${image.directory.path}")
    private String directoryPath;

    @Override
    public String saveImage(MultipartFile image) {

        try {

            String imageUrl = directoryPath + image.getOriginalFilename();
            File file = new File(directoryPath);

            if (!file.exists()) {
                file.mkdir();
            }

            image.transferTo(new File(imageUrl));
            return imageUrl;

        } catch (IOException e) {
            return "Error:" + e.getMessage();
        }
    }
}
