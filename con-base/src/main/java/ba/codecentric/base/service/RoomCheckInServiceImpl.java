package ba.codecentric.base.service;

import ba.codecentric.base.domain.Room;
import ba.codecentric.base.domain.RoomCheckIn;
import ba.codecentric.base.helper.CheckInRequest;
import ba.codecentric.base.repository.RoomCheckInRepository;
import ba.codecentric.base.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        ArrayList<Room> availableRooms = new ArrayList<>();

        for (Room room : roomRepository.findRoomsWithNumberOfPeople(checkInRequest.getNumberOfKids(), checkInRequest.getNumberOfAdults())) {
            ArrayList<RoomCheckIn> checkInRooms = (ArrayList<RoomCheckIn>) roomCheckInRepository.findByRoom(room);

            if (checkInRooms.size() == 0) {
                availableRooms.add(room);
            } else {
                boolean removed = false;
                for (RoomCheckIn roomCheckIn : roomCheckInRepository.findByRoom(room)) {
                    if (checkInRequest.getCheckOut().before(roomCheckIn.getCheckIn()) || checkInRequest.getCheckIn().after(roomCheckIn.getCheckOut())) {
                        if (!removed) {
                            if (!availableRooms.contains(room)) {
                                availableRooms.add(room);
                            }
                        }
                    } else {
                        availableRooms.remove(room);
                        removed = true;
                    }
                }
            }
        }
        return availableRooms;
    }

    private Integer getPassword() {
        return ThreadLocalRandom.current().nextInt(1000, 9999);
    }

}
