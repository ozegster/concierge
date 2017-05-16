package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Facility;
import ba.codecentric.base.service.FacilityService;
import ba.codecentric.base.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping(value = "/facilities", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Facility saveFacility(@RequestPart("image") MultipartFile image, @Valid @RequestPart("facility") Facility facility) throws IOException {
        facility.setImage(imageService.saveImage(image.getInputStream(), image.getOriginalFilename()));
        return facilityService.saveFacility(facility);
    }

    @GetMapping(value = "/facilities")
    public boolean isExistingName(@RequestParam("name") String name){
        return facilityService.isExistingName(name);
    }
}
