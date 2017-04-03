package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Hotel;
import ba.codecentric.base.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @RequestMapping(value = "/hotel/save", method = RequestMethod.POST)
    public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel) {
        return new ResponseEntity<Hotel>(hotelService.saveHotel(hotel), HttpStatus.OK);

    }

    @RequestMapping(value = "/hotel/get", method = RequestMethod.GET)
    public ResponseEntity<Hotel> getHotel() {
        Hotel hotel = hotelService.getHotel();
        return (hotel == null) ? new ResponseEntity<Hotel>(new Hotel(), HttpStatus.OK) : new ResponseEntity<Hotel>(hotel, HttpStatus.OK);

    }
}
