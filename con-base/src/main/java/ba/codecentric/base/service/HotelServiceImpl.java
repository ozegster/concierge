package ba.codecentric.base.service;

import ba.codecentric.base.domain.Hotel;
import ba.codecentric.base.repository.HotelRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final Logger LOG = Logger.getLogger(HotelServiceImpl.class);

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    @Transactional
    public Hotel saveHotel(Hotel hotel) {
        if (hotel.getImageLogo() == null) {
            LOG.error("Error while saving hotel imageLogo, imageService did not saved imageLogo, return: 'null'");
            return new Hotel();
        }
        LOG.info("Hotel " + hotel.getName() + " is saved");

        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotel() {
        return hotelRepository.findFirstByOrderById();
    }

    @Override
    public Hotel findById(Integer hotelId) {
        return hotelRepository.findOne(hotelId);
    }

    @Override
    public String findImagePathById(Integer hotelId){
        return hotelRepository.findImagePathById(hotelId);
    }

    @Override
    public boolean isExistingName(String name) {
        return hotelRepository.existsByName(name);
    }
}

