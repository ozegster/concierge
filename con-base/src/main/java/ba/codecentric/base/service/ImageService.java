package ba.codecentric.base.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface ImageService {

    String saveImage(InputStream image, String name) throws IOException;
    InputStream loadImage(String name) throws IOException;
    void deleteImage(String imageName);
    boolean doesImageExist(String imageName);
    String encodeImage(String imageName) throws FileNotFoundException, IOException;

}
