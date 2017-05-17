package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Room;
import ba.codecentric.base.service.RoomService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RoomController {

    private final RoomService roomService;
    private final static Logger LOG = Logger.getLogger(RoomController.class);

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping(value = "/rooms")
    public void save(@Valid @RequestBody Room room) {
        roomService.saveRoom(room);
        LOG.info("Save Room with number: " + room.getNumber());
    }

    @GetMapping(value = "/rooms")
    public List<Room> getRooms() {
        return roomService.getAllRooms();
    }

    @DeleteMapping(value = "/rooms/{id}")
    public void delete(@PathVariable Integer id) {
        roomService.deleteRoom(id);
        LOG.info("Delete Room with id: " + id);
    }
}
