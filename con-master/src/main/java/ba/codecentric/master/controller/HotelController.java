package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Hotel;
import ba.codecentric.base.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @RequestMapping(value = "/hotel/save", method = RequestMethod.POST)
    public void saveHotelInfo(@RequestBody Hotel hotel) {
        hotelService.saveHotel(hotel);
    }
}
