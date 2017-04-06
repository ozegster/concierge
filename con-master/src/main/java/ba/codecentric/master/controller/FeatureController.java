package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Feature;
import ba.codecentric.base.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeatureController {

    private FeatureService featureService;

    @Autowired
    public FeatureController(FeatureService featureService){
        this.featureService = featureService;
    }

    @RequestMapping(name="features", method= RequestMethod.GET)
    public List<Feature> getFeatures(){
        return featureService.getAllFeatures();
    }
}
