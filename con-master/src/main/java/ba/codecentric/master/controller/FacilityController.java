package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Facility;
import ba.codecentric.base.service.FacilityService;
import ba.codecentric.base.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FacilityController {

    private final FacilityService facilityService;
    private final ImageService imageService;

    @Autowired
    public FacilityController(FacilityService facilityService, ImageService imageService) {
        this.facilityService = facilityService;
        this.imageService = imageService;
    }

    @RequestMapping(value = "/facility", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public Facility saveFacility(@RequestPart("image") MultipartFile image, @RequestPart("facility") Facility facility) {
        facility.setImage(imageService.saveImage(image));
        return facilityService.saveFacility(facility);
    }
}
