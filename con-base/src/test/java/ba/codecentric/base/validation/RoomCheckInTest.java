package ba.codecentric.base.validation;

import ba.codecentric.base.domain.Room;
import ba.codecentric.base.domain.RoomCheckIn;
import ba.codecentric.base.service.RoomCheckInServiceImpl;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomCheckInTest {

    private static Validator validator;
    private final static Logger LOG = Logger.getLogger(RoomCheckInTest.class);

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        RoomCheckInServiceImpl roomCheckInService;
    }

    @Test
    public void roomCheckInIsEmpty() {
        RoomCheckIn roomCheckIn = new RoomCheckIn();
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(7, validations.size());
    }

    @Test
    public void roomCheckInHasAllData() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(0, validations.size());
    }

    @Test
    public void nameIsEmpty() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        roomCheckIn.setName(null);
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(1, validations.size());
        assertEquals("Please enter guest name", validations.iterator().next().getMessage());
    }

    @Test
    public void nameIsTooLong() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        roomCheckIn.setName("Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger ");
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(1, validations.size());
        assertEquals("Name is too long, 128 characters allowed", validations.iterator().next().getMessage());
    }

    @Test
    public void nameIsValid() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        roomCheckIn.setName("Arnold");
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(0, validations.size());
    }

    @Test
    public void lastNameIsEmpty() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        roomCheckIn.setLastName(null);
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(1, validations.size());
        assertEquals("Please enter guest last name", validations.iterator().next().getMessage());
    }

    @Test
    public void lastNameIsTooLong() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        roomCheckIn.setLastName("Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger Arnold Schwarzenegger ");
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(1, validations.size());
        assertEquals("Last name is too long, 128 characters allowed", validations.iterator().next().getMessage());
    }

    @Test
    public void lastNameIsValid() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        roomCheckIn.setLastName("Schwarzenegger");
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(0, validations.size());
    }

    @Test
    public void emailIsEmpty() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        roomCheckIn.setEmail(null);
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(1, validations.size());
        assertEquals("Please enter a email", validations.iterator().next().getMessage());
    }

    @Test
    public void emailIsTooLong() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        roomCheckIn.setEmail("arnold_schwarzeneggerarnold_schwarzeneggerarnold_schwarzeneggerarnold_schwarzenegger@hotelhotelhotelhotelhotelhotelhotelhotelhotel.com");
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(1, validations.size());
        assertEquals("Email is too long, 45 characters allowed", validations.iterator().next().getMessage());
    }

    @Test
    public void emailIsWrong() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        roomCheckIn.setEmail("arnold@arnold");
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(1, validations.size());
        assertEquals("Please enter a valid email address e.g. info@hotel.com", validations.iterator().next().getMessage());
    }

    @Test
    public void emailIsValid() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        roomCheckIn.setEmail("arnold@arnold.com");
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(0, validations.size());
    }

    @Test
    public void checkInIsEmpty() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        roomCheckIn.setCheckIn(null);
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(1, validations.size());
        assertEquals("Please enter check-in date", validations.iterator().next().getMessage());
    }

    @Test
    public void checkInIsValid() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        roomCheckIn.setCheckIn(new Date());
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(0, validations.size());
    }

    @Test
    public void checkOutIsEmpty() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        roomCheckIn.setCheckOut(null);
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(1, validations.size());
        assertEquals("Please enter check-out date", validations.iterator().next().getMessage());
    }

    @Test
    public void checkOutIsValid() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        roomCheckIn.setCheckOut(new Date());
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(0, validations.size());
    }

    @Test
    public void numberOfAdultsIsNull() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        roomCheckIn.setNumberOfAdults(null);
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(1, validations.size());
        assertEquals("Please enter number of adults", validations.iterator().next().getMessage());
    }

    @Test
    public void numberOfAdultsIsTooSmall() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        roomCheckIn.setNumberOfAdults(0);
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(1, validations.size());
        assertEquals("Minimum number of adults is 1", validations.iterator().next().getMessage());
    }

    @Test
    public void numberOfAdultsIsTooBig() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        roomCheckIn.setNumberOfAdults(20);
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(1, validations.size());
        assertEquals("Maximum number of adults is 6", validations.iterator().next().getMessage());
    }

    @Test
    public void numberOfAdultsIsValid() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        roomCheckIn.setNumberOfAdults(3);
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(0, validations.size());
    }

    @Test
    public void numberOfKidsIsNull() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        roomCheckIn.setNumberOfKids(null);
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(1, validations.size());
        assertEquals("Please enter number of kids", validations.iterator().next().getMessage());
    }

    @Test
    public void numberOfKidsIsTooSmall() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        roomCheckIn.setNumberOfKids(-1);
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(1, validations.size());
        assertEquals("Minimum number of kids is 0", validations.iterator().next().getMessage());
    }

    @Test
    public void numberOfKidsIsTooBig() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        roomCheckIn.setNumberOfKids(6);
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(1, validations.size());
        assertEquals("Maximum number of kids is 3", validations.iterator().next().getMessage());
    }

    @Test
    public void numberOfKidsIsValid() {
        RoomCheckIn roomCheckIn = getRoomCheckIn();
        roomCheckIn.setNumberOfKids(2);
        Set<ConstraintViolation<RoomCheckIn>> validations = validator.validate(roomCheckIn);
        assertEquals(0, validations.size());
    }

    private RoomCheckIn getRoomCheckIn() {
        RoomCheckIn roomCheckIn = new RoomCheckIn();
        roomCheckIn.setName("Arnold");
        roomCheckIn.setLastName("Schwarzenegger");
        roomCheckIn.setEmail("arnold_s@gmail.com");
        roomCheckIn.setCheckIn(new Date());
        roomCheckIn.setCheckOut(new Date(System.currentTimeMillis() + 604800000));
        roomCheckIn.setNumberOfAdults(3);
        roomCheckIn.setNumberOfKids(3);
        roomCheckIn.setPassword(9999);
        roomCheckIn.setRoom(new Room());
        return roomCheckIn;

    }
}
