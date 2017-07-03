package ba.codecentric.master.controller;

import ba.codecentric.master.google.NearbyGoogleService;
import com.google.maps.errors.ApiException;
import com.google.maps.model.Photo;
import com.google.maps.model.PlacesSearchResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class NearbyGoogleController {

    private NearbyGoogleService googleService;

    @Autowired
    public NearbyGoogleController(NearbyGoogleService googleService){
        this.googleService = googleService;
    }

    @GetMapping(value = "/places")
    public PlacesSearchResponse getPlaces(@RequestParam("data")String keywordForSearching) throws InterruptedException, ApiException, IOException {
        System.out.println("ctrl " + keywordForSearching);
        return googleService.getPlaces(keywordForSearching);
    }

    @PostMapping(value = "/images",produces="text/plain")
    public String getImage(@RequestBody Photo photo) throws InterruptedException, ApiException, IOException {
        return googleService.getImage(photo);
    }
}
