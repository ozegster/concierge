package ba.codecentric.base.service;

import java.io.InputStream;

public interface ImageService {

    String saveImage(InputStream image, String name);

    String getImageName(String oldName);

}
