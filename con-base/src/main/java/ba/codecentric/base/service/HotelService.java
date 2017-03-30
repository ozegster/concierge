package ba.codecentric.base.service;

import org.springframework.stereotype.Service;
import ba.codecentric.base.domain.Hotel;

@Service
public interface HotelService {

    void saveHotel(Hotel hotel);

}
