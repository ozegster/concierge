package ba.codecentric.base.service;

import ba.codecentric.base.domain.Room;
import ba.codecentric.base.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    @Transactional
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteRoom(Integer id) {
        roomRepository.delete(id);
    }
}
