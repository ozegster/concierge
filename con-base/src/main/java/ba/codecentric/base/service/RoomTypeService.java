package ba.codecentric.base.service;

import ba.codecentric.base.domain.RoomType;

import java.util.List;

public interface RoomTypeService {

    RoomType saveRoom(RoomType roomType);

    List<RoomType> getAllRoomTypes();

    RoomType getRoomTypeById(Integer id);
}
