package ba.codecentric.base.service;

import ba.codecentric.base.domain.RoomType;
import ba.codecentric.base.repository.RoomTypeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import java.util.List;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;
    private final Logger LOG = Logger.getLogger(RoomTypeServiceImpl.class);

    @Autowired
    public RoomTypeServiceImpl(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    @Transactional
    public RoomType saveRoomType(RoomType roomType) {
        if (roomType.getImage() == null) {
            LOG.error("Error while saving room type, imageService did not saved image, return: 'null'");
            return new RoomType();
        }
        LOG.info("Room type " + roomType.getName() + " is saved");
        return roomTypeRepository.save(roomType);
    }

    @Override
    @Transactional
    public List<RoomType> getAllRoomTypes(){
        return roomTypeRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteRoomType(Integer roomTypeId){
        roomTypeRepository.deleteById(roomTypeId);
    }

    @Override
    public RoomType findById(Integer roomTypeId) {
        return roomTypeRepository.findOne(roomTypeId);
    }

    @Override
    public String findImagePathById(Integer roomTypeId){
        return roomTypeRepository.findImagePathById(roomTypeId);
    }

}
