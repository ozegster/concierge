package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Country;
import ba.codecentric.base.domain.FacilityType;
import ba.codecentric.base.service.FacilityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FacilityTypeController {

    private FacilityTypeService facilityTypeService;

    @Autowired
    public FacilityTypeController(FacilityTypeService facilityTypeService) {
        this.facilityTypeService = facilityTypeService;
    }

    @RequestMapping(value = "/facilitytype", method = RequestMethod.GET)
    public List<FacilityType> getFacilityTypes() {
        return facilityTypeService.getAllFacilityTypes();
    }
}
