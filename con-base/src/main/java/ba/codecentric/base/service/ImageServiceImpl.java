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
    public void saveImage(MultipartFile image) {
        try {
            image.transferTo(new File(directoryPath + image.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
