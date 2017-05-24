package ba.codecentric.base.service;

import ba.codecentric.base.domain.Hotel;

public interface HotelService {

    Hotel saveHotel(Hotel hotel);

    Hotel getHotel();

    Hotel findById(Integer hotelId);

    String findImagePathById(Integer hotelId);

    boolean isExistingName(String name);
}
