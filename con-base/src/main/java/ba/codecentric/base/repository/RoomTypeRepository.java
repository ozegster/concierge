package ba.codecentric.base.repository;

import ba.codecentric.base.domain.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {

    void deleteById(Integer roomTypeId);

    @Query("SELECT roomType.image FROM RoomType roomType WHERE roomType.id = ?1")
    String findImageById(Integer id);
}
