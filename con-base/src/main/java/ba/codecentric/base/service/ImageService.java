package ba.codecentric.base.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImage(MultipartFile image);

}
