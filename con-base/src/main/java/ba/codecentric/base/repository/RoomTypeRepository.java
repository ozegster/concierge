package ba.codecentric.base.repository;

import ba.codecentric.base.domain.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {

    boolean existsByName(String name);
}
