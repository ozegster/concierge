package ba.codecentric.base.repository;

import ba.codecentric.base.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    Hotel findFirstByOrderById();

}
