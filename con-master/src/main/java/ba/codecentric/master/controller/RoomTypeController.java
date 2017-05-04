package ba.codecentric.master.controller;


import ba.codecentric.base.domain.RoomType;
import ba.codecentric.base.service.ImageService;
import ba.codecentric.base.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class RoomTypeController {

    private final RoomTypeService roomTypeService;
    private final ImageService imageService;

    @Value ("${image.directory.path}")
    String directoryPath;

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
            return roomTypeService.saveRoom(roomType);
        }
        return new RoomType();
    }

    @RequestMapping(value = "/room-type", method = RequestMethod.GET)
    public List<RoomType> getRoomType(){
        return roomTypeService.getAllRoomTypes();
    }

    @RequestMapping(value = "/directory" ,method = RequestMethod.GET, produces="text/plain")
    public String getDirectoryPath(){
        return directoryPath;
    }

    @RequestMapping(value = "/delete-room-type", method = RequestMethod.POST)
    public RoomType deleteRoomType(@RequestBody RoomType roomType){
        roomTypeService.deleteRoomType(roomType);
        return roomType;
    }
}
