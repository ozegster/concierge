package ba.codecentric.base.repository;

import ba.codecentric.base.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("SELECT r FROM Room r WHERE r.roomType.numberOfKids>?1 AND r.roomType.numberOfPeople>?2")
    Iterable<Room> getAvailableRooms(Integer numberOfKids, Integer numberOfPeople);
}
