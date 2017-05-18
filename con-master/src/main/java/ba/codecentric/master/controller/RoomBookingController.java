package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Room;
import ba.codecentric.base.domain.RoomBooking;
import ba.codecentric.base.helper.BookingRequest;
import ba.codecentric.base.service.RoomBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomBookingController {

    private final RoomBookingService roomBookingService;

    @Autowired
    public RoomBookingController(RoomBookingService roomBookingService) {
        this.roomBookingService = roomBookingService;
    }

    @PostMapping(value = "/room/booking")
    public RoomBooking saveRoomBooking(@RequestBody RoomBooking roomBooking) {
        return roomBookingService.saveRoomBooking(roomBooking);
    }

    @PostMapping(value = "/get/available/rooms")
    public Iterable<Room> getAvailableRooms(@RequestBody BookingRequest bookingRequest) {
        return roomBookingService.findAvailableRooms(bookingRequest);
    }

}
