package ba.codecentric.base.service;

import ba.codecentric.base.domain.Facility;

import java.util.List;

public interface FacilityService {

    Facility saveFacility(Facility facility);

    boolean isExistingName(String name);

    List<Facility> getAllFacilities();

    void deleteFacility(Integer facilityId);

    Facility findById(Integer facilityId);

    String findImagePathById(Integer facilityId);
}
