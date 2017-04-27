package ba.codecentric.base.service;


import ba.codecentric.base.domain.RoomType;

public interface RoomTypeService {

    RoomType saveRoom(RoomType roomType);

    boolean isExistingName(String name);
}
