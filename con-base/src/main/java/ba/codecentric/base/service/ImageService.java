package ba.codecentric.base.service;

import java.io.IOException;
import java.io.InputStream;

public interface ImageService {

    String saveImage(InputStream image, String name) throws IOException;

    String getImageName(String oldName);

}
