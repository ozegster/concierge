package ba.codecentric.base.repository;

import ba.codecentric.base.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoomRepository extends JpaRepository<Room, Integer> {
}
