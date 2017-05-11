package ba.codecentric.base.service;

import ba.codecentric.base.domain.Facility;
import ba.codecentric.base.repository.FacilityRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository facilityRepository;
    private final Logger LOG = Logger.getLogger(FacilityServiceImpl.class);

    @Autowired
    public FacilityServiceImpl(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    @Override
    @Transactional
    public Facility saveFacility(Facility facility) {
        if (facility.getImage() == null) {
            LOG.error("Error while saving facility image, imageService did not saved image, return: 'null'");
            return new Facility();
        }
        LOG.info("Facility " + facility.getFacilityName() + " is saved");
        return facilityRepository.save(facility);
    }

}
