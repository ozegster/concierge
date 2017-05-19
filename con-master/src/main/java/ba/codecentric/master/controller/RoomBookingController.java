package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Room;
import ba.codecentric.base.domain.RoomCheckIn;
import ba.codecentric.base.helper.CheckInRequest;
import ba.codecentric.base.service.RoomCheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomBookingController {

    private final RoomCheckInService roomCheckInService;

    @Autowired
    public RoomBookingController(RoomCheckInService roomCheckInService) {
        this.roomCheckInService = roomCheckInService;
    }

    @PostMapping(value = "/room/check-in")
    public RoomCheckIn saveRoomBooking(@RequestBody RoomCheckIn roomCheckIn) {
        return roomCheckInService.saveRoomCheckIn(roomCheckIn);
    }

    @PutMapping(value = "/get/available/rooms")
    public Iterable<Room> getAvailableRooms(@RequestBody CheckInRequest checkInRequest) {
        Iterable<Room> rooms = roomCheckInService.findAvailableRooms(checkInRequest);
        return roomCheckInService.findAvailableRooms(checkInRequest);
    }

}
