package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Facility;
import ba.codecentric.base.service.FacilityService;
import ba.codecentric.base.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

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
        String imageName = "";
        Integer facilityId = facility.getId();

        if (facilityId != null) {
            imageName = facilityService.findImagePathById(facilityId);
            if (imageService.doesImageExist(imageName)) {
                imageService.deleteImage(imageName);
            }
        }

        String fileName = imageService.saveImage(image.getInputStream(), image.getOriginalFilename());
        if (fileName != null) {
            facility.setImage(fileName);
            return facilityService.saveFacility(facility);
        }
        return new Facility();
    }

    @GetMapping(value = "/facilities")
    public List<Facility> getAllFacilities() {
        return facilityService.getAllFacilities();
    }

    @DeleteMapping(value = "/facilities/{facilityId}")
    public @ResponseBody ResponseEntity<String> deleteFacility(@PathVariable Integer facilityId) throws Exception {
        Facility facility = facilityService.findById(facilityId);
        String deleteMessage = "[\"message: Facility is found\"]";
        if (facility != null) {
            facilityService.deleteFacility(facilityId);
            imageService.deleteImage(facility.getImage());
            return new ResponseEntity<>(deleteMessage, HttpStatus.OK);
        } else {
            deleteMessage = "[\"message: Facility was not found\"]";
            return new ResponseEntity<>(deleteMessage, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/facility/image/{imageName:.+}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public InputStreamResource getImage(@PathVariable String imageName) throws IOException {
        return new InputStreamResource(imageService.loadImage(imageName));
    }
}
