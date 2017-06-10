package ba.codecentric.master.controller;

import ba.codecentric.base.domain.RoomType;
import ba.codecentric.base.service.ImageService;
import ba.codecentric.base.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @PostMapping(value = "/room-types", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RoomType saveRoom(@RequestPart("image") MultipartFile image, @Valid @RequestPart("roomType") RoomType roomType) throws IOException {


        String imageName = "";
        Integer roomTypeId = roomType.getId();

        if(roomTypeId != null){
            imageName = roomTypeService.findImagePathById(roomTypeId);
            if(imageService.doesImageExist(imageName)) {
                imageService.deleteImage(imageName);
            }
        }

        String fileName = imageService.saveImage(image.getInputStream(), image.getOriginalFilename());
        if (fileName != null) {
            roomType.setImage(fileName);
            return roomTypeService.saveRoomType(roomType);
        }
        return new RoomType();

    }

    @DeleteMapping(value = "/room-type/{roomTypeId}")
    public void deleteRoomType(@PathVariable Integer roomTypeId) throws Exception{

        RoomType roomType = roomTypeService.findById(roomTypeId);

        if(roomType != null) {
            roomTypeService.deleteRoomType(roomTypeId);
            imageService.deleteImage(roomType.getImage());
        }
    }

    @GetMapping(value = "/room-types")
    public List<RoomType> getRoomTypes() throws IOException{
        List<RoomType>list = roomTypeService.getAllRoomTypes();
        for(RoomType roomType : list){
            roomType.setImage(imageService.encodeImage(roomType.getImage()));
        }
        return list;
    }

    @GetMapping(value = "/room-types/image/{imageName:.+}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public InputStreamResource getImage(@PathVariable String imageName) throws IOException {
        return new InputStreamResource(imageService.loadImage(imageName));
    }
}
