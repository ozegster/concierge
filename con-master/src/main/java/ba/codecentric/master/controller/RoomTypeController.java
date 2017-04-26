package ba.codecentric.master.controller;

import ba.codecentric.base.domain.RoomType;
import ba.codecentric.base.service.ImageService;
import ba.codecentric.base.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class RoomTypeController {

    private final RoomTypeService roomTypeService;
    private final ImageService imageService;

    @Autowired
    public RoomTypeController(RoomTypeService roomTypeService, ImageService imageService) {
        this.roomTypeService = roomTypeService;
        this.imageService = imageService;
    }

    @RequestMapping(value = "/room-type", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RoomType saveRoom(@RequestPart("image") MultipartFile image, @Valid @RequestPart("roomType") RoomType roomType) throws IOException {
        String fileName = imageService.saveImage(image.getInputStream(), image.getOriginalFilename());
        if (fileName != null) {
            roomType.setImage(fileName);
            return roomTypeService.saveRoomType(roomType);
        }
        return new RoomType();
    }

    @RequestMapping(value = "/room-types", method = RequestMethod.GET)
    public List<RoomType> getRoomTypes() {
        return roomTypeService.getAllRoomTypes();
    }

    @RequestMapping(value = "/room-types/image", method = RequestMethod.GET, produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public InputStreamResource getImage(@RequestParam String imageName) throws IOException {
        return new InputStreamResource(imageService.loadImage(imageName));
    }
}
