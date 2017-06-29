package ba.codecentric.base.service;

import ba.codecentric.base.domain.Room;

import java.util.List;

public interface RoomService {

    Room saveRoom(Room room);

    List<Room> getAllRooms();

    void deleteRoom(Integer id);
}
