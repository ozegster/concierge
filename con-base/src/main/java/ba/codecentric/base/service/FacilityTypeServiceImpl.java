package ba.codecentric.base.service;

import ba.codecentric.base.domain.FacilityType;
import ba.codecentric.base.repository.FacilityTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityTypeServiceImpl implements FacilityTypeService {

    private FacilityTypeRepository facilityTypeRepository;

    @Autowired
    public FacilityTypeServiceImpl(FacilityTypeRepository facilityTypeRepository) {
        this.facilityTypeRepository = facilityTypeRepository;
    }

    @Override
    public List<FacilityType> getAllFacilityTypes() {
        return facilityTypeRepository.findAll();
    }
}
