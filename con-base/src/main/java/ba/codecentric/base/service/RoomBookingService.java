package ba.codecentric.base.service;

import ba.codecentric.base.domain.Room;
import ba.codecentric.base.domain.RoomBooking;
import ba.codecentric.base.helper.BookingRequest;

public interface RoomBookingService {

    RoomBooking saveRoomBooking(RoomBooking roomBooking);

    Iterable<Room> findAvailableRooms(BookingRequest bookingRequest);
}
