package ba.codecentric.base.repository;

import ba.codecentric.base.domain.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomBookingRepository extends JpaRepository<RoomBooking, Integer> {

    RoomBooking findByPassword(Integer password);
}
