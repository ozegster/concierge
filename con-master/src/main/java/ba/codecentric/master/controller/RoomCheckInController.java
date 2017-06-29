package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Room;
import ba.codecentric.base.domain.RoomCheckIn;
import ba.codecentric.base.helper.CheckInRequest;
import ba.codecentric.base.service.RoomCheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomCheckInController {

    private final RoomCheckInService roomCheckInService;

    @Autowired
    public RoomCheckInController(RoomCheckInService roomCheckInService) {
        this.roomCheckInService = roomCheckInService;
    }

    @PostMapping(value = "/room/check-in")
    public RoomCheckIn saveRoomBooking(@RequestBody RoomCheckIn roomCheckIn) {
        return roomCheckInService.saveRoomCheckIn(roomCheckIn);
    }

    @PutMapping(value = "/get/available/rooms")
    public Iterable<Room> getAvailableRooms(@RequestBody CheckInRequest checkInRequest) {
        return roomCheckInService.findAvailableRooms(checkInRequest);
    }

    @GetMapping(value = "/room/{password}")
    public RoomCheckIn getRoomCheckInByPassword(@PathVariable Integer password) {
        return roomCheckInService.findRoomCheckInByPassword(password);
    }

}
