package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Hotel;
import ba.codecentric.base.service.HotelService;
import ba.codecentric.base.service.ImageService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

@RestController
public class HotelController {

    private final HotelService hotelService;
    private final ImageService imageService;
    private final static Logger LOG = Logger.getLogger(HotelController.class);

    @Autowired
    public HotelController(HotelService hotelService, ImageService imageService) {
        this.hotelService = hotelService;
        this.imageService = imageService;
    }

    @PostMapping(value = "/hotels", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Hotel saveHotel(@RequestPart("image") MultipartFile image, @Valid @RequestPart("hotel") Hotel hotel) throws IOException {
        LOG.info("Save Hotel info: " + hotel.getName());
        String imageName = "";
        Integer hotelId = hotel.getId();

        if (hotelId != null) {
            imageName = hotelService.findImagePathById(hotelId);
            if (imageService.doesImageExist(imageName)) {
                imageService.deleteImage(imageName);
            }
        }

        String fileName = imageService.saveImage(image.getInputStream(), image.getOriginalFilename());
        if (fileName != null) {
            hotel.setImageLogo(fileName);
            return hotelService.saveHotel(hotel);
        }
        return new Hotel();
    }

    @GetMapping(value = "/hotel/imageLogo/{imageName:.+}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public InputStreamResource getImage(@PathVariable String imageName) throws IOException {
        return new InputStreamResource(imageService.loadImage(imageName));
    }

    @GetMapping(value = "/hotels")
    public Hotel getHotel() {
        Hotel hotel = hotelService.getHotel();
        return (hotel == null) ? new Hotel() : hotel;

    }

    @GetMapping(value = "/test")
    public Hotel test() throws Exception{


        Hotel hotel = hotelService.getHotel();

        File f = new File("/home/bojan/concierge/images/" + hotel.getImageLogo());

        InputStream fis = new FileInputStream(f);

        byte byteArray[] = new byte[(int)f.length()];
        fis.read(byteArray);

        String imageString = "data:image/png;base64," + Base64.encodeBase64String(byteArray);
        hotel.setImageLogo(imageString);//not affect db
        return hotel;
    }
}