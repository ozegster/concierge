package ba.codecentric.base.repository;

import ba.codecentric.base.domain.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoomRepository extends JpaRepository<RoomType, Integer> {
}
