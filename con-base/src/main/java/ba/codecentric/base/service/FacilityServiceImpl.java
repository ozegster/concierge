package ba.codecentric.base.service;

import ba.codecentric.base.domain.Facility;
import ba.codecentric.base.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository facilityRepository;

    @Autowired
    public FacilityServiceImpl(FacilityRepository facilityRepository) { this.facilityRepository = facilityRepository;}

    @Override
    public Facility saveFacility(Facility facility){
        return facilityRepository.save(facility);
    }

}
