package ba.codecentric.base.validation;

import ba.codecentric.base.domain.BedType;
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
public class RoomTypeTest {


    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void roomTypeIsEmpty() {
        RoomType roomType = new RoomType();
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(6, validations.size());
    }

    @Test
    public void roomTypeHasAllData() {
        RoomType roomType = getRoomType();
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(0, validations.size());
    }

    @Test
    public void nameIsEmpty() {
        RoomType roomType = getRoomType();
        roomType.setName(" ");
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Please enter name of the room type", validations.iterator().next().getMessage());
    }

    @Test
    public void nameIsTooLong() {
        RoomType roomType = getRoomType();
        roomType.setName("abcd abcd abcd abcd abcd abcd abcd abcd abcd abcd abcd abcd abcd abcd abcd abcd abcd ");
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Room type name is too long, 80 characters allowed", validations.iterator().next().getMessage());
    }

    @Test
    public void nameIsNull() {
        RoomType roomType = getRoomType();
        roomType.setName(null);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Please enter name of the room type", validations.iterator().next().getMessage());
    }

    @Test
    public void nameIsValid() {
        RoomType roomType = getRoomType();
        roomType.setName("room name");
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(0, validations.size());
    }

    @Test
    public void numberOfPeopleIsNull() {
        RoomType roomType = getRoomType();
        roomType.setNumberOfPeople(null);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Please enter number of people", validations.iterator().next().getMessage());
    }

    @Test
    public void numberOfPeopleIsTooSmall() {
        RoomType roomType = getRoomType();
        roomType.setNumberOfPeople(0);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Minimum number of people is 1", validations.iterator().next().getMessage());
    }

    @Test
    public void numberOfPeopleIsTooBig() {
        RoomType roomType = getRoomType();
        roomType.setNumberOfPeople(7);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Maximum number of people is 6", validations.iterator().next().getMessage());
    }

    @Test
    public void numberOfPeopleIsValidMinimum() {
        RoomType roomType = getRoomType();
        roomType.setNumberOfPeople(1);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(0, validations.size());
    }

    @Test
    public void numberOfPeopleIsValidMaximum() {
        RoomType roomType = getRoomType();
        roomType.setNumberOfPeople(6);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(0, validations.size());
    }

    @Test
    public void numberOfKidsIsNull() {
        RoomType roomType = getRoomType();
        roomType.setNumberOfKids(null);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Please enter number of kids", validations.iterator().next().getMessage());
    }

    @Test
    public void numberOfKidsIsTooSmall() {
        RoomType roomType = getRoomType();
        roomType.setNumberOfKids(-1);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Minimum number of kids is 0", validations.iterator().next().getMessage());
    }

    @Test
    public void numberOfKidsIsTooBig() {
        RoomType roomType = getRoomType();
        roomType.setNumberOfKids(4);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Maximum number of kids is 3", validations.iterator().next().getMessage());
    }

    @Test
    public void numberOfKidsIsValidMinimum() {
        RoomType roomType = getRoomType();
        roomType.setNumberOfKids(0);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(0, validations.size());
    }

    @Test
    public void numberOfKidsIsValidMaximum() {
        RoomType roomType = getRoomType();
        roomType.setNumberOfKids(3);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(0, validations.size());
    }

    @Test
    public void sizeIsNull() {
        RoomType roomType = getRoomType();
        roomType.setSize(null);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Please enter size of the room", validations.iterator().next().getMessage());
    }

    @Test
    public void sizeIsValid() {
        RoomType roomType = getRoomType();
        roomType.setSize(10);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(0, validations.size());
    }

    @Test
    public void imageIsNull() {
        RoomType roomType = getRoomType();
        roomType.setImage(null);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Please enter image of the room", validations.iterator().next().getMessage());
    }

    @Test
    public void bedTypeIsNull() {
        RoomType roomType = getRoomType();
        roomType.setBedType(null);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Please select bed type of the room", validations.iterator().next().getMessage());
    }

    @Test
    public void bedTypeIsValid() {
        RoomType roomType = getRoomType();
        roomType.setBedType(new BedType());
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(0, validations.size());
    }

    @Test
    public void imageIsValid() {
        RoomType roomType = getRoomType();
        roomType.setImage("image.jpg");
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(0, validations.size());
    }

    private RoomType getRoomType() {
        RoomType roomType = new RoomType();
        roomType.setName("room");
        roomType.setNumberOfPeople(2);
        roomType.setNumberOfKids(2);
        roomType.setSize(20);
        roomType.setImage("image.jpg");
        roomType.setBedType(new BedType());

        return roomType;
    }
}
