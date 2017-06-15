package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Room;
import ba.codecentric.base.domain.RoomType;
import ba.codecentric.base.service.ImageService;
import ba.codecentric.base.service.RoomService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;
    private final ImageService imageService;
    private final static Logger LOG = Logger.getLogger(RoomController.class);

    @Autowired
    public RoomController(RoomService roomService,ImageService imageService) {
        this.roomService = roomService;
        this.imageService = imageService;
    }

    @PostMapping
    public void save(@Valid @RequestBody Room room) {
        roomService.saveRoom(room);
        LOG.info("Save Room with number: " + room.getNumber());
    }

    @GetMapping
    public List<Room> getRooms() throws IOException{
        List<Room>list = roomService.getAllRooms();

        for(Room room : list){
            RoomType roomType = room.getRoomType();
            String image = roomType.getImage();
            if(!image.startsWith("data:image/png;base64,")) {
                roomType.setImage(imageService.encodeImage(roomType.getImage()));
            }
        }
        return list;
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        roomService.deleteRoom(id);
        LOG.info("Delete Room with id: " + id);
    }

    @GetMapping(value = "/{id}")
    public Room getRoom(@PathVariable Integer id){
        return roomService.getRoom(id);
    }
}
