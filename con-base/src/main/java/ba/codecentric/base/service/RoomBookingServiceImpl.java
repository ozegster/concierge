package ba.codecentric.base.service;

import ba.codecentric.base.domain.RoomBooking;
import ba.codecentric.base.repository.RoomBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomBookingServiceImpl implements RoomBookingService {

    private final RoomBookingRepository roomBookingRepository;

    @Autowired
    public RoomBookingServiceImpl(RoomBookingRepository roomBookingRepository) {
        this.roomBookingRepository = roomBookingRepository;
    }

    @Override
    public RoomBooking saveRoomBooking(RoomBooking roomBooking) {
        return roomBookingRepository.save(roomBooking);
    }
}
