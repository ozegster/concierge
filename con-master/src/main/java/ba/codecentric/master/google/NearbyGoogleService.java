package ba.codecentric.master.google;

import com.google.maps.GeoApiContext;
import com.google.maps.NearbySearchRequest;
import com.google.maps.PhotoRequest;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.google.maps.model.Photo;
import com.google.maps.model.PhotoResult;
import com.google.maps.model.PlacesSearchResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NearbyGoogleService {

    private static String GOOGLE_API_KEY;
    private static GeoApiContext geoApiContext = new GeoApiContext().setApiKey("AIzaSyDwaKMhq0FWSR7bIyNHd9vmaZ4G1eiOTJM");

    @Value("${google.api.key}")
    public void setGoogleApiKey(String key) {
        GOOGLE_API_KEY = key;
    }


    public PlacesSearchResponse getPlaces(String keywordForSearching) throws InterruptedException, ApiException, IOException {

        LatLng location = new LatLng(44.7273374, 18.0773216);
        NearbySearchRequest request = PlacesApi.nearbySearchQuery(geoApiContext, location)
                .radius(500).keyword(keywordForSearching);

        PlacesSearchResponse response = request.await();

        return response;
    }

    public String getImage(Photo photos) throws InterruptedException, ApiException, IOException {

        PhotoResult photoResult = null;
        PhotoRequest photoRequest = PlacesApi.photo(geoApiContext, photos.photoReference)
                .maxHeight(photos.height).maxWidth(photos.width);


        photoResult = photoRequest.await();

        return getBase64String(photoResult.imageData);
    }

    private String getBase64String(byte[] array){
        return "data:image/png;base64," + Base64.encodeBase64String(array);
    }


}
