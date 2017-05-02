package ba.codecentric.base.validation;

import ba.codecentric.base.domain.Facility;
import ba.codecentric.base.domain.FacilityType;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class FacilityTest {

    private static Validator validator;

    private final Logger logger = Logger.getLogger(FacilityTest.class);


    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void facilityIsEmpty() {
        Facility facility = new Facility();
        Set<ConstraintViolation<Facility>> validations = validator.validate(facility);
        assertEquals(3, validations.size());
    }

    @Test
    public void facilityHasAllData() {
        Facility facility = getFacility();
        Set<ConstraintViolation<Facility>> validations = validator.validate(facility);
        assertEquals(0, validations.size());
    }

    @Test
    public void facilityNameIsEmpty() {
        Facility facility = getFacility();
        logger.debug("Hello this is a debug message");
        logger.info("Hello this is an info message");
        facility.setFacilityName("");
        Set<ConstraintViolation<Facility>> validations = validator.validate(facility);
        assertEquals(1, validations.size());
        assertEquals("Please enter name of the facility", validations.iterator().next().getMessage());
    }

    @Test
    public void facilityNameIsTooLong() {
        Facility facility = getFacility();
        facility.setFacilityName("facility name facility name facility name facility name facility name facility name facility name facility name facility name facility name facility name facility name");
        Set<ConstraintViolation<Facility>> validations = validator.validate(facility);
        assertEquals(1, validations.size());
        assertEquals("Facility name is too long, 80 characters allowed", validations.iterator().next().getMessage());
    }

    @Test
    public void facilityNameIsNull() {
        Facility facility = getFacility();
        facility.setFacilityName(null);
        Set<ConstraintViolation<Facility>> validations = validator.validate(facility);
        assertEquals(1, validations.size());
        assertEquals("Please enter name of the facility", validations.iterator().next().getMessage());
    }

    @Test
    public void facilityDescriptionIsNull() {
        Facility facility = getFacility();
        facility.setDescription(null);
        Set<ConstraintViolation<Facility>> validations = validator.validate(facility);
        assertEquals(1, validations.size());
        assertEquals("Please add facility description", validations.iterator().next().getMessage());
    }

    @Test
    public void descriptionIsTooLong() {
        Facility facility = getFacility();
        facility.setDescription("Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description ");
        Set<ConstraintViolation<Facility>> validations = validator.validate(facility);
        assertEquals(1, validations.size());
        assertEquals("Description is too long, 400 characters allowed", validations.iterator().next().getMessage());
    }

    @Test
    public void imageIsNull() {
        Facility facility = getFacility();
        facility.setImage(null);
        Set<ConstraintViolation<Facility>> validations = validator.validate(facility);
        assertEquals(1, validations.size());
        assertEquals("Please add facility image", validations.iterator().next().getMessage());
    }

    @Test
    public void imageIsTooLong() {
        Facility facility = getFacility();
        facility.setImage("image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image image ");
        Set<ConstraintViolation<Facility>> validations = validator.validate(facility);
        assertEquals(1, validations.size());
        assertEquals("Address of image is too long, 128 characters allowed", validations.iterator().next().getMessage());
    }

    @Test
    public void floorIsTooHigh() {
        Facility facility = getFacility();
        facility.setFloor(15);
        Set<ConstraintViolation<Facility>> validations = validator.validate(facility);
        assertEquals(1, validations.size());
        assertEquals("Floor is too high, allowed to 10", validations.iterator().next().getMessage());
    }

    @Test
    public void floorIsTooLow() {
        Facility facility = getFacility();
        facility.setFloor(-5);
        Set<ConstraintViolation<Facility>> validations = validator.validate(facility);
        assertEquals(1, validations.size());
        assertEquals("Floor is too low, allowed to -2", validations.iterator().next().getMessage());
    }

    private Facility getFacility() {

        Facility facility = new Facility();
        facility.setFacilityName("facility name");
        facility.setDescription("description");
        facility.setFloor(5);
        facility.setImage("image");
        facility.setFacilityType(new FacilityType());

        return facility;
    }

}
