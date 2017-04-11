package ba.codecentric.master.controller;

import ba.codecentric.base.domain.RoomType;
import ba.codecentric.base.service.ImageService;
import ba.codecentric.base.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class RoomTypeController {

    private RoomTypeService roomTypeService;
    private ImageService imageService;

    @Autowired
    public RoomTypeController(RoomTypeService roomTypeService, ImageService imageService) {
        this.roomTypeService = roomTypeService;
        this.imageService = imageService;
    }

    @RequestMapping(value = "/roomtype", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public RoomType saveRoom(@RequestPart("image") MultipartFile image, @RequestPart("roomType") RoomType roomType) {
        System.out.println(roomType);
        imageService.saveImage(image);
        roomType.setImage(image.getOriginalFilename());
        return roomTypeService.saveRoom(roomType);
    }
}
