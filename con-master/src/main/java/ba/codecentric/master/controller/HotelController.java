package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Hotel;
import ba.codecentric.base.service.HotelService;
import ba.codecentric.base.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.IOException;
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

    @GetMapping(value = "/hotels")
    public Hotel getHotel() throws IOException{
        Hotel hotel = hotelService.getHotel();
        hotel.setImageLogo(imageService.encodeImage(hotel.getImageLogo()));
        return (hotel == null) ? new Hotel() : hotel;

    }
}