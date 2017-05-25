package ba.codecentric.base.repository;

import ba.codecentric.base.domain.RoomCheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;


public interface RoomCheckInRepository extends JpaRepository<RoomCheckIn, Integer> {

    RoomCheckIn findByPassword(Integer password);

    @Query(value = "SELECT r FROM RoomCheckIn r WHERE r.checkOut<?1 AND r.password>0")
    Iterable<RoomCheckIn> findByCheckOutDate(Date checkOut);
}
