package ba.codecentric.base.repository;

import ba.codecentric.base.domain.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, Integer> {

    boolean existsByFacilityName(String name);

}
