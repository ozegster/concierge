package ba.codecentric.master.controller;


import ba.codecentric.base.domain.RoomType;
import ba.codecentric.base.service.ImageService;
import ba.codecentric.base.service.RoomTypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
public class RoomTypeController {

    private final RoomTypeService roomTypeService;
    private final ImageService imageService;
    private final Logger log = Logger.getLogger(RoomTypeController.class);

    @Autowired
    public RoomTypeController(RoomTypeService roomTypeService, ImageService imageService) {
        this.roomTypeService = roomTypeService;
        this.imageService = imageService;
    }

    @RequestMapping(value = "/room-type", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RoomType saveRoom(@RequestPart("image") MultipartFile image, @Valid @RequestPart("roomType") RoomType roomType) {

        try {

            String fileName = imageService.saveImage(image.getInputStream(), image.getOriginalFilename());
            if (fileName != null) {
                roomType.setImage(fileName);
                log.info("Save room type: " + roomType.getName());
                return roomTypeService.saveRoom(roomType);
            }
            log.error("Error while saving room type image");
            return new RoomType();
        } catch (IOException e) {
            log.error("Error while saving room type. Message: " + e.getMessage());
        }
        return new RoomType();


    }
}
