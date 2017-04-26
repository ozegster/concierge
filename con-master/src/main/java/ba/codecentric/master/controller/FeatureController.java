package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Feature;
import ba.codecentric.base.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeatureController {

    private final FeatureService featureService;

    @Autowired
    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }

    @GetMapping(value = "/features")
    public List<Feature> getFeatures() {
        return featureService.getAllFeatures();
    }


}
