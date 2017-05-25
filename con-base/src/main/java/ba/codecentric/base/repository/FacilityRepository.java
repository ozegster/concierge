package ba.codecentric.base.repository;

import ba.codecentric.base.domain.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FacilityRepository extends JpaRepository<Facility, Integer> {

    void deleteById(Integer facilityId);

    @Query("SELECT facility.image FROM Facility facility WHERE facility.id = ?1")
    String findImagePathById(Integer id);
}
