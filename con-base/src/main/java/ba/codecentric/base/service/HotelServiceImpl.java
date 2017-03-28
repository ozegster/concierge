package ba.codecentric.base.service;

import ba.codecentric.base.domain.Hotel;
import ba.codecentric.base.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class HotelServiceImpl implements HotelService {

    private HotelRepository hotelRepo;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepo) {
        this.hotelRepo = hotelRepo;
    }

    @Override
    public void saveHotel(Hotel hotel) {

        hotelRepo.save(hotel);
    }


}
