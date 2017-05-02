package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Facility;
import ba.codecentric.base.service.FacilityService;
import ba.codecentric.base.service.ImageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
public class FacilityController {

    private final FacilityService facilityService;
    private final ImageService imageService;
    private final Logger log = Logger.getLogger(FacilityController.class);

    @Autowired
    public FacilityController(FacilityService facilityService, ImageService imageService) {
        this.facilityService = facilityService;
        this.imageService = imageService;
    }

    @RequestMapping(value = "/facilities", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Facility saveFacility(@RequestPart("image") MultipartFile image, @Valid @RequestPart("facility") Facility facility) {

        try {

            String fileName = imageService.saveImage(image.getInputStream(), image.getOriginalFilename());
            if (fileName != null) {
                facility.setImage(fileName);
                log.info("Save facility: " + facility.getFacilityName());
                return facilityService.saveFacility(facility);
            }
            log.error("Error while saving facility image");
            return new Facility();

        } catch (IOException e) {
            log.error("Error while saving facility. Message: " + e.getMessage());
        }
        return new Facility();
    }
}
