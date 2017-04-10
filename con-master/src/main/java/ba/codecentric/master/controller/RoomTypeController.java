package ba.codecentric.master.controller;

import ba.codecentric.base.domain.RoomType;
import ba.codecentric.base.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomTypeController {

    private RoomTypeService roomTypeService;

    @Autowired
    public RoomTypeController(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @RequestMapping(value = "/roomtype", method = RequestMethod.POST)
    public RoomType saveRoom(@RequestBody RoomType roomType) {
        return roomTypeService.saveRoom(roomType);
    }
}
