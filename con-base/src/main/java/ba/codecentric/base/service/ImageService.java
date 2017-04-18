package ba.codecentric.base.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String saveImage(MultipartFile image);
}
