package ba.codecentric.base.service;

import ba.codecentric.base.domain.Hotel;
import ba.codecentric.base.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HotelServiceImpl implements HotelService{

    private HotelRepository hotelRepo;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepo){
        this.hotelRepo = hotelRepo;
    }

    @Override
    @Transactional
    public void saveHotel(Hotel hotel){
        hotelRepo.save(hotel);
    }
}
