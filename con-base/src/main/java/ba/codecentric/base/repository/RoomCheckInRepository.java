package ba.codecentric.base.repository;

import ba.codecentric.base.domain.Room;
import ba.codecentric.base.domain.RoomCheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;


public interface RoomCheckInRepository extends JpaRepository<RoomCheckIn, Integer> {

    RoomCheckIn findByPassword(Integer password);

    @Query(value = "SELECT r FROM RoomCheckIn r WHERE r.checkOut<?1 AND r.password>0")
    Iterable<RoomCheckIn> findByCheckOutDate(Date checkOut);

    @Query(value = "select r.room from RoomCheckIn r join r.room where r.room.roomType.numberOfKids>= :numberOfKids and r.room.roomType.numberOfPeople>= :numberOfPeople and ((r.checkIn> :checkIn and r.checkIn> :checkOut) or(r.checkOut< :checkIn and r.checkOut< :checkOut)) group by r.room")
    Iterable<Room> getAvailableRooms(@Param("numberOfKids") Integer numberOfKids, @Param("numberOfPeople") Integer numberOfPeople, @Param("checkIn") Date checkIn, @Param("checkOut") Date checkOut);
}
