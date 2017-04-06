package ba.codecentric.base.repository;


import ba.codecentric.base.domain.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature,Integer> {
}
