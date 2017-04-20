package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Facility;
import ba.codecentric.base.service.FacilityService;
import ba.codecentric.base.service.ImageService;
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

    @Autowired
    public FacilityController(FacilityService facilityService, ImageService imageService) {
        this.facilityService = facilityService;
        this.imageService = imageService;
    }

    @RequestMapping(value = "/facilities", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Facility saveFacility(@RequestPart("image") MultipartFile image, @Valid @RequestPart("facility") Facility facility) throws IOException {

        String fileName = imageService.saveImage(image.getInputStream(), image.getOriginalFilename());
        if (fileName != null) {
            facility.setImage(fileName);
            return facilityService.saveFacility(facility);
        }
        return new Facility();
    }
}
