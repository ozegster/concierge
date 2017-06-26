package ba.codecentric.base.repository;

import ba.codecentric.base.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query(value = "select r from Room r where r.roomType.numberOfKids>= :numberOfKids and r.roomType.numberOfPeople>= :numberOfPeople")
    Iterable<Room> findRoomsWithNumberOfPeople(@Param("numberOfKids") Integer numberOfKids, @Param("numberOfPeople") Integer numberOfPeople);
}
