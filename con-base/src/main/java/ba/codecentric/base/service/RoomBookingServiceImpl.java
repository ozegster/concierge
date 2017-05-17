package ba.codecentric.base.service;

import ba.codecentric.base.domain.Hotel;
import ba.codecentric.base.domain.Room;
import ba.codecentric.base.domain.RoomBooking;
import ba.codecentric.base.helper.BookingRequest;
import ba.codecentric.base.repository.HotelRepository;
import ba.codecentric.base.repository.RoomBookingRepository;
import ba.codecentric.base.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomBookingServiceImpl implements RoomBookingService {

    private final RoomBookingRepository roomBookingRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public RoomBookingServiceImpl(RoomBookingRepository roomBookingRepository, HotelRepository hotelRepository, RoomRepository roomRepository) {
        this.roomBookingRepository = roomBookingRepository;
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomBooking saveRoomBooking(RoomBooking roomBooking) {

        Hotel hotel = hotelRepository.findFirstByOrderById();

        roomBooking.getCheckIn().setTime(hotel.getCheckIn().getTime());
        roomBooking.getCheckOut().setTime(hotel.getCheckOut().getTime());

        return roomBookingRepository.save(roomBooking);
    }

    @Override
    public Iterable<Room> findAvailableRooms(BookingRequest bookingRequest) {
        return roomRepository.getAvailableRooms(bookingRequest.getNumberOfKids(), bookingRequest.getNumberOfPeople());
    }

}
