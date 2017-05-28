package ba.codecentric.base.service;

import ba.codecentric.base.domain.Room;
import ba.codecentric.base.domain.RoomCheckIn;
import ba.codecentric.base.helper.CheckInRequest;
import ba.codecentric.base.repository.RoomCheckInRepository;
import ba.codecentric.base.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class RoomCheckInServiceImpl implements RoomCheckInService {

    private final RoomCheckInRepository roomCheckInRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public RoomCheckInServiceImpl(RoomCheckInRepository roomCheckInRepository, RoomRepository roomRepository) {
        this.roomCheckInRepository = roomCheckInRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomCheckIn saveRoomCheckIn(RoomCheckIn roomCheckIn) {
        boolean passwordCheck = true;
        while (passwordCheck) {
            Integer password = getPassword();
            if (roomCheckInRepository.findByPassword(password) == null) {
                roomCheckIn.setPassword(password);
                passwordCheck = false;
            }
        }
        return roomCheckInRepository.save(roomCheckIn);
    }

    @Override
    public Iterable<Room> findAvailableRooms(CheckInRequest checkInRequest) {
        return roomCheckInRepository.getAvailableRooms(checkInRequest.getNumberOfKids(), checkInRequest.getNumberOfAdults(), checkInRequest.getCheckIn(), checkInRequest.getCheckOut());
    }

    private Integer getPassword() {
        return ThreadLocalRandom.current().nextInt(1000, 9999);
    }

}
