package ba.codecentric.base.repository;

import ba.codecentric.base.domain.RoomCheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomCheckInRepository extends JpaRepository<RoomCheckIn, Integer> {

    RoomCheckIn findByPassword(Integer password);
}
