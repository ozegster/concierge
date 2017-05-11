package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Hotel;
import ba.codecentric.base.service.HotelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class HotelController {

    private final HotelService hotelService;
    private final static Logger LOG = Logger.getLogger(HotelController.class);

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping(value = "/hotels")
    public Hotel saveHotel(@Valid @RequestBody Hotel hotel) {
        LOG.info("Save Hotel info: " + hotel.getName());
        return hotelService.saveHotel(hotel);

    }

    @GetMapping(value = "/hotels")
    public Hotel getHotel() {
        Hotel hotel = hotelService.getHotel();
        return (hotel == null) ? new Hotel() : hotel;

    }
}
