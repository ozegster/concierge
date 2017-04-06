package ba.codecentric.base.service;

import ba.codecentric.base.domain.Feature;
import ba.codecentric.base.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureServiceImpl implements FeatureService{

    private final FeatureRepository featureRepository;

    @Autowired
    public FeatureServiceImpl(FeatureRepository featureRepository){
        this.featureRepository = featureRepository;
    }

    @Override
    public List<Feature> getAllFeatures(){
        return featureRepository.findAll();
    }
}
