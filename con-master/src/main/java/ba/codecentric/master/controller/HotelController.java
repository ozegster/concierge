package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Hotel;
import ba.codecentric.base.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@RestController
public class HotelController {

    private final HotelService hotelService;

    private final Validator validator;

    @Autowired
    public HotelController(HotelService hotelService, Validator validator) {
        this.hotelService = hotelService;
        this.validator = validator;
    }

    @RequestMapping(value = "/hotel", method = RequestMethod.POST)
    public Hotel saveHotel(@RequestBody Hotel hotel) throws Exception {

        Set<ConstraintViolation<Hotel>> constraintViolations = validator.validate(hotel);

        if (constraintViolations.size() > 0)
            throw new Exception(constraintViolations.iterator().next().getMessage());

        return hotelService.saveHotel(hotel);

    }

    @RequestMapping(value = "/hotel", method = RequestMethod.GET)
    public Hotel getHotel() {
        Hotel hotel = hotelService.getHotel();
        return (hotel == null) ? new Hotel() : hotel;

    }
}
