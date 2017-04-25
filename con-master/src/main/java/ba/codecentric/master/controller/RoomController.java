package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Room;
import ba.codecentric.base.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @RequestMapping(value = "/room", method = RequestMethod.POST)
    public void save(@RequestBody Room room) {
        roomService.saveRoom(room);
    }
}
