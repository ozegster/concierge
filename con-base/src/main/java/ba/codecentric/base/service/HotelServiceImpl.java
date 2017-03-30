package ba.codecentric.base.service;

import ba.codecentric.base.domain.Hotel;
import ba.codecentric.base.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class HotelServiceImpl implements HotelService {

<<<<<<< HEAD
    private HotelRepository hotelRepository;
=======
    private final HotelRepository hotelRepository;
>>>>>>> origin/integration

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void saveHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }


}
