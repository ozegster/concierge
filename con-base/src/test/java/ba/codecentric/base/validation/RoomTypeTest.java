package ba.codecentric.base.validation;

import ba.codecentric.base.domain.BedType;
import ba.codecentric.base.domain.RoomType;
import ba.codecentric.base.service.RoomTypeService;
import ba.codecentric.base.validation.util.BeanValidatorTestUtils;
import ba.codecentric.base.validation.validator.UniqueValidator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoomTypeTest {

    @Mock
    private RoomTypeService roomTypeService;

    @InjectMocks
    private UniqueValidator uniqueValidator;

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void roomTypeIsEmpty() {
        RoomType roomType = new RoomType();
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(6, validations.size());
    }

    @Test
    public void roomTypeHasAllData() {
        RoomType roomType = getRoomType();
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(0, validations.size());
    }

    @Test
    public void nameIsEmpty() {
        RoomType roomType = getRoomType();
        roomType.setName(" ");
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Please enter name of the room type", validations.iterator().next().getMessage());
    }

    @Test
    public void nameIsTooLong() {
        RoomType roomType = getRoomType();
        roomType.setName("abcd abcd abcd abcd abcd abcd abcd abcd abcd abcd abcd abcd abcd abcd abcd abcd abcd ");
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Room type name is too long, 80 characters allowed", validations.iterator().next().getMessage());
    }

    @Test
    public void nameIsNull() {
        RoomType roomType = getRoomType();
        roomType.setName(null);
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Please enter name of the room type", validations.iterator().next().getMessage());
    }

    @Test
    public void nameIsValid() {
        RoomType roomType = getRoomType();
        roomType.setName("room name");
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(0, validations.size());
    }

    @Test
    public void numberOfPeopleIsNull() {
        RoomType roomType = getRoomType();
        roomType.setNumberOfPeople(null);
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Please enter number of people", validations.iterator().next().getMessage());
    }

    @Test
    public void numberOfPeopleIsTooSmall() {
        RoomType roomType = getRoomType();
        roomType.setNumberOfPeople(0);
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Minimum number of people is 1", validations.iterator().next().getMessage());
    }

    @Test
    public void numberOfPeopleIsTooBig() {
        RoomType roomType = getRoomType();
        roomType.setNumberOfPeople(7);
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Maximum number of people is 6", validations.iterator().next().getMessage());
    }

    @Test
    public void numberOfPeopleIsValidMinimum() {
        RoomType roomType = getRoomType();
        roomType.setNumberOfPeople(1);
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(0, validations.size());
    }

    @Test
    public void numberOfPeopleIsValidMaximum() {
        RoomType roomType = getRoomType();
        roomType.setNumberOfPeople(6);
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(0, validations.size());
    }

    @Test
    public void numberOfKidsIsNull() {
        RoomType roomType = getRoomType();
        roomType.setNumberOfKids(null);
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Please enter number of kids", validations.iterator().next().getMessage());
    }

    @Test
    public void numberOfKidsIsTooSmall() {
        RoomType roomType = getRoomType();
        roomType.setNumberOfKids(-1);
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Minimum number of kids is 0", validations.iterator().next().getMessage());
    }

    @Test
    public void numberOfKidsIsTooBig() {
        RoomType roomType = getRoomType();
        roomType.setNumberOfKids(4);
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Maximum number of kids is 3", validations.iterator().next().getMessage());
    }

    @Test
    public void numberOfKidsIsValidMinimum() {
        RoomType roomType = getRoomType();
        roomType.setNumberOfKids(0);
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(0, validations.size());
    }

    @Test
    public void numberOfKidsIsValidMaximum() {
        RoomType roomType = getRoomType();
        roomType.setNumberOfKids(3);
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(0, validations.size());
    }

    @Test
    public void sizeIsNull() {
        RoomType roomType = getRoomType();
        roomType.setSize(null);
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Please enter size of the room", validations.iterator().next().getMessage());
    }

    @Test
    public void sizeIsTooSmall() {
        RoomType roomType = getRoomType();
        roomType.setSize(0);
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Minimum size is 5", validations.iterator().next().getMessage());
    }

    @Test
    public void sizeIsTooBig() {
        RoomType roomType = getRoomType();
        roomType.setSize(128);
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Maximum size is 127", validations.iterator().next().getMessage());
    }

    @Test
    public void sizeIsValidMinimum() {
        RoomType roomType = getRoomType();
        roomType.setSize(5);
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(0, validations.size());
    }

    @Test
    public void sizeIsValidMaximum() {
        RoomType roomType = getRoomType();
        roomType.setSize(127);
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(0, validations.size());
    }

    @Test
    public void sizeIsValid() {
        RoomType roomType = getRoomType();
        roomType.setSize(10);
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(0, validations.size());
    }

    @Test
    public void imageIsNull() {
        RoomType roomType = getRoomType();
        roomType.setImage(null);
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Please enter image of the room", validations.iterator().next().getMessage());
    }

    @Test
    public void bedTypeIsNull() {
        RoomType roomType = getRoomType();
        roomType.setBedType(null);
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(1, validations.size());
        assertEquals("Please select bed type of the room", validations.iterator().next().getMessage());
    }

    @Test
    public void bedTypeIsValid() {
        RoomType roomType = getRoomType();
        roomType.setBedType(new BedType());
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
        Set<ConstraintViolation<RoomType>> validations = validator.validate(roomType);
        assertEquals(0, validations.size());
    }

    @Test
    public void imageIsValid() {
        RoomType roomType = getRoomType();
        roomType.setImage("image.jpg");
        uniqueValidator.initialize(null);
        BeanValidatorTestUtils.replaceValidatorInContext(validator, uniqueValidator, roomType);
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
