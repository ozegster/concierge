package ba.codecentric.base.service;

import ba.codecentric.base.domain.Room;
import ba.codecentric.base.domain.RoomBooking;
import ba.codecentric.base.helper.BookingRequest;
import ba.codecentric.base.repository.RoomBookingRepository;
import ba.codecentric.base.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class RoomBookingServiceImpl implements RoomBookingService {

    private final RoomBookingRepository roomBookingRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public RoomBookingServiceImpl(RoomBookingRepository roomBookingRepository, RoomRepository roomRepository) {
        this.roomBookingRepository = roomBookingRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomBooking saveRoomBooking(RoomBooking roomBooking) {
        roomBooking.setPassword(getPassword());
        return roomBookingRepository.save(roomBooking);
    }

    @Override
    public Iterable<Room> findAvailableRooms(BookingRequest bookingRequest) {
        return roomRepository.getAvailableRooms(bookingRequest.getNumberOfKids(), bookingRequest.getNumberOfPeople());
    }

    private Integer getPassword() {
        return ThreadLocalRandom.current().nextInt(1000, 9999);
    }

}
