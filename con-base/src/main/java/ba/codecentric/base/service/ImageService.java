package ba.codecentric.base.service;

import java.io.InputStream;

public interface ImageService {

    boolean saveImage(InputStream image, String name);

}
