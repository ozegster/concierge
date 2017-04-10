package ba.codecentric.base.service;

import ba.codecentric.base.domain.RoomType;
import ba.codecentric.base.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    @Autowired
    public RoomTypeServiceImpl(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public RoomType saveRoom(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }
}
