package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Facility;
import ba.codecentric.base.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FacilityController {

    private final FacilityService facilityService;

    @Autowired
    public  FacilityController(FacilityService facilityService) {this.facilityService = facilityService;}

    @RequestMapping(value = "/facility", method = RequestMethod.POST)
    public Facility saveFacility(@RequestBody Facility facility){
        return facilityService.saveFacility(facility);
    }

    @RequestMapping(value = "/facility", method = RequestMethod.GET)
    public Facility getFacility(){
        Facility facility = facilityService.getFacility();
        return (facility == null)? new Facility() : facility;
    }

}
