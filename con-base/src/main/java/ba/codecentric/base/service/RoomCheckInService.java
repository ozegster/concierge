package ba.codecentric.base.service;

import ba.codecentric.base.domain.Room;
import ba.codecentric.base.domain.RoomCheckIn;
import ba.codecentric.base.helper.CheckInRequest;

public interface RoomCheckInService {

    RoomCheckIn saveRoomCheckIn(RoomCheckIn roomCheckIn);

    Iterable<Room> findAvailableRooms(CheckInRequest checkInRequest);

    RoomCheckIn findRoomCheckInByPassword(Integer password);
}
