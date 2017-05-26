package ba.codecentric.base.service;

import ba.codecentric.base.domain.Room;
import ba.codecentric.base.domain.RoomCheckIn;
import ba.codecentric.base.helper.CheckInRequest;
import ba.codecentric.base.repository.RoomCheckInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class RoomCheckInServiceImpl implements RoomCheckInService {

    private final RoomCheckInRepository roomCheckInRepository;

    @Autowired
    public RoomCheckInServiceImpl(RoomCheckInRepository roomCheckInRepository) {
        this.roomCheckInRepository = roomCheckInRepository;
    }

    @Override
    public RoomCheckIn saveRoomCheckIn(RoomCheckIn roomCheckIn) {
        roomCheckIn.setPassword(getPassword());
        return roomCheckInRepository.save(roomCheckIn);
    }

    @Override
    public Iterable<Room> findAvailableRooms(CheckInRequest checkInRequest) {
        return roomCheckInRepository.getAvailableRooms(checkInRequest.getNumberOfKids(), checkInRequest.getNumberOfAdults());
    }

    private Integer getPassword() {
        return ThreadLocalRandom.current().nextInt(1000, 9999);
    }

}
