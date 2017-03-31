package ba.codecentric.base.service;

import ba.codecentric.base.domain.Hotel;

import java.util.List;

public interface HotelService {

    Hotel saveHotel(Hotel hotel);

    List<Hotel> getHotels();

}
