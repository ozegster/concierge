package ba.codecentric.base.repository;


import ba.codecentric.base.domain.Bed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BedRepository extends JpaRepository<Bed, Integer> {
}
