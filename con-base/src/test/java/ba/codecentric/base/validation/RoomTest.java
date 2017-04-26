package ba.codecentric.base.validation;

import ba.codecentric.base.domain.Room;
import ba.codecentric.base.domain.RoomType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void roomIsEmpty() {
        Room room = new Room();
        Set<ConstraintViolation<Room>> violations = validator.validate(room);
        assertEquals(3, violations.size());
    }

    @Test
    public void roomIsValid() {
        Room room = getRoom();
        Set<ConstraintViolation<Room>> violations = validator.validate(room);
        assertEquals(0, violations.size());
    }

    @Test
    public void roomNumberIsNull() {
        Room room = getRoom();
        room.setNumber(null);
        Set<ConstraintViolation<Room>> violations = validator.validate(room);
        assertEquals(1, violations.size());
        assertEquals("Please enter the room number", violations.iterator().next().getMessage());
    }

    @Test
    public void roomNumberIsValid() {
        Room room = getRoom();
        room.setNumber(10);
        Set<ConstraintViolation<Room>> violations = validator.validate(room);
        assertEquals(0, violations.size());
    }

    @Test
    public void floorNumberIsNull() {
        Room room = getRoom();
        room.setFloorNumber(null);
        Set<ConstraintViolation<Room>> violations = validator.validate(room);
        assertEquals(1, violations.size());
        assertEquals("Please enter the floor number", violations.iterator().next().getMessage());
    }

    @Test
    public void floorNumberIsValid() {
        Room room = getRoom();
        room.setFloorNumber(5);
        Set<ConstraintViolation<Room>> violations = validator.validate(room);
        assertEquals(0, violations.size());
    }

    @Test
    public void roomTypeIsNull() {
        Room room = getRoom();
        room.setRoomType(null);
        Set<ConstraintViolation<Room>> violations = validator.validate(room);
        assertEquals(1, violations.size());
        assertEquals("Please select the room type", violations.iterator().next().getMessage());
    }

    @Test
    public void roomTypeIsSelected() {
        Room room = getRoom();
        room.setRoomType(new RoomType());
        Set<ConstraintViolation<Room>> violations = validator.validate(room);
        assertEquals(0, violations.size());
    }

    private Room getRoom() {
        Room room = new Room();
        room.setNumber(1);
        room.setFloorNumber(1);
        room.setRoomType(new RoomType());
        return room;
    }
}
