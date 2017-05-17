package ba.codecentric.base.validation;

import ba.codecentric.base.domain.Country;
import ba.codecentric.base.domain.Hotel;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import java.sql.Time;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration
public class HotelTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void hotelIsEmpty() {
        Hotel hotel = new Hotel();
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(12, validations.size());
    }

    @Test
    public void hotelHasAllData() {
        Hotel hotel = getHotel();
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(0, validations.size());
    }

    @Test
    public void hotelNameIsEmpty() {
        Hotel hotel = getHotel();
        hotel.setName(" ");
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Please enter name of the hotel", validations.iterator().next().getMessage());
    }

    @Test
    public void hotelNameIsTooLong() {
        Hotel hotel = getHotel();
        hotel.setName("lkjsdfh glksjdh fglksjhdf glskjdhf gljkshd flgjkh slfdhgslkjdh glkjshd flgjhs ldfjh glsjdkh gljksh dlfjhg sljkdh gfljkh dslfjhg slkjdhfg l");
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Name is too long, 128 characters allowed", validations.iterator().next().getMessage());
    }

    @Test
    public void hotelNameIsNull() {
        Hotel hotel = getHotel();
        hotel.setName(null);
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Please enter name of the hotel", validations.iterator().next().getMessage());
    }

    @Test
    public void ratingIsZero() {
        Hotel hotel = getHotel();
        hotel.setRating(0);
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Rating must be greater than 0", validations.iterator().next().getMessage());
    }

    @Test
    public void ratingIsOutOfBounds() {
        Hotel hotel = getHotel();
        hotel.setRating(6);
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Rating must be less than 5", validations.iterator().next().getMessage());
    }

    @Test
    public void addressIsNull() {
        Hotel hotel = getHotel();
        hotel.setAddress(null);
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Please enter street and number", validations.iterator().next().getMessage());
    }

    @Test
    public void addressIsTooLong() {
        Hotel hotel = getHotel();
        hotel.setAddress("lkjsdfh glksjdh fglksjhdf glskjdhf gljkshd flgjkh slfdhgslkjdh glkjshd flgjhs ldfjh glsjdkh gljksh dlfjhg sljkdh gfljkh dslfjhg slkjdhfg l");
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Address is too long, 128 characters allowed", validations.iterator().next().getMessage());
    }

    @Test
    public void addressIsEmpty() {
        Hotel hotel = getHotel();
        hotel.setAddress(" ");
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Please enter street and number", validations.iterator().next().getMessage());
    }

    @Test
    public void zipIsNull() {
        Hotel hotel = getHotel();
        hotel.setZip(null);
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Please enter zip code", validations.iterator().next().getMessage());
    }

    @Test
    public void zipIsWrong() {
        Hotel hotel = getHotel();
        hotel.setZip("999999");
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Please enter a valid zip code e.g. 43452 (5 digit required)", validations.iterator().next().getMessage());
    }

    @Test
    public void zipHasLetter() {
        Hotel hotel = getHotel();
        hotel.setZip("6985y");
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Please enter a valid zip code e.g. 43452 (5 digit required)", validations.iterator().next().getMessage());
    }

    @Test
    public void cityIsNull() {
        Hotel hotel = getHotel();
        hotel.setCity(null);
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Please enter city", validations.iterator().next().getMessage());
    }

    @Test
    public void cityIsEmpty() {
        Hotel hotel = getHotel();
        hotel.setCity(" ");
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Please enter city", validations.iterator().next().getMessage());
    }

    @Test
    public void cityIsTooLong() {
        Hotel hotel = getHotel();
        hotel.setCity("lkjsdfh glksjdh fglksjhdf glskjdhf gljkshd flgjkh slfdhgslkjdh glkjshd flgjhs ldfjh glsjdkh gljksh dlfjhg sljkdh gfljkh dslfjhg slkjdhfg l");
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("City name is too long, 64 characters allowed", validations.iterator().next().getMessage());
    }

    @Test
    public void phoneIsNull() {
        Hotel hotel = getHotel();
        hotel.setPhone(null);
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Please enter a phone number", validations.iterator().next().getMessage());
    }

    @Test
    public void phoneIsEmpty() {
        Hotel hotel = getHotel();
        hotel.setPhone(" ");
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Please enter a phone number", validations.iterator().next().getMessage());
    }

    @Test
    public void phoneIsTooLong() {
        Hotel hotel = getHotel();
        hotel.setPhone("phone, phone, phone, phone, phone, phone, phone");
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Phone is too long, 45 characters allowed", validations.iterator().next().getMessage());
    }

    @Test
    public void faxIsNull() {
        Hotel hotel = getHotel();
        hotel.setFax(null);
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Please enter a fax", validations.iterator().next().getMessage());
    }

    @Test
    public void faxIsEmpty() {
        Hotel hotel = getHotel();
        hotel.setFax(" ");
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Please enter a fax", validations.iterator().next().getMessage());
    }

    @Test
    public void faxIsTooLong() {
        Hotel hotel = getHotel();
        hotel.setFax("fax, fax, fax, fax, fax, fax, fax, fax, fax, fax, fax");
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Fax is too long, 45 characters allowed", validations.iterator().next().getMessage());
    }

    @Test
    public void emailIsNull() {
        Hotel hotel = getHotel();
        hotel.setEmail(null);
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Please enter a email", validations.iterator().next().getMessage());
    }

    @Test
    public void emailIsWrong() {
        Hotel hotel = getHotel();
        hotel.setEmail("wronemail");
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Please enter a valid email address e.g. info@hotel.com", validations.iterator().next().getMessage());
    }

    @Test
    public void emailIsTooLong() {
        Hotel hotel = getHotel();
        hotel.setEmail("emailemailemailemailemailemailemailemail@emailemailemailemail.com");
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Email is too long, 45 characters allowed", validations.iterator().next().getMessage());
    }

    @Test
    public void websiteIsWrong() {
        Hotel hotel = getHotel();
        hotel.setWebsite("website");
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Please enter a valid url e.g. www.hotel.com", validations.iterator().next().getMessage());
    }

    @Test
    public void websiteIsTooLong() {
        Hotel hotel = getHotel();
        hotel.setWebsite("www.websitewebsitewebsitewebsitewebsitewebsitewebsitewebsitewebsitewebsitewebsite.com");
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Website is too long, 45 characters allowed", validations.iterator().next().getMessage());
    }

    @Test
    public void descriptionIsTooLong() {
        Hotel hotel = getHotel();
        hotel.setDescription("Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description ");
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Description is too long, 500 characters allowed", validations.iterator().next().getMessage());
    }

    @Test
    public void checkOutIsWrong() {
        Hotel hotel = getHotel();
        hotel.setCheckOut("00");
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Please enter a valid Check-out time", validations.iterator().next().getMessage());
    }

    @Test
    public void checkInIsWrong() {
        Hotel hotel = getHotel();
        hotel.setCheckIn("11");
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Please enter a valid Check-in time", validations.iterator().next().getMessage());
    }

    @Test
    public void checkOutIsNull() {
        Hotel hotel = getHotel();
        hotel.setCheckOut(null);
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Please enter a valid Check-out time", validations.iterator().next().getMessage());
    }

    @Test
    public void checkInIsNull() {
        Hotel hotel = getHotel();
        hotel.setCheckIn(null);
        Set<ConstraintViolation<Hotel>> validations = validator.validate(hotel);
        assertEquals(1, validations.size());
        assertEquals("Please enter a valid Check-in time", validations.iterator().next().getMessage());
    }

    @Test
    public void isParsableFalse() {
        Hotel hotel = getHotel();
        boolean result = hotel.isParsable("1010");
        assertEquals(false, result);
    }
    @Test
    public void isParsableTrue() {
        Hotel hotel = getHotel();
        boolean result = hotel.isParsable("11:10");
        assertEquals(true, result);
    }

    @Test
    public void parseStringToTimeIsNull() {
        Hotel hotel = getHotel();
        Time result = hotel.parseStringToTime("null");
        assertEquals(null, result);
    }

    @Test
    public void parseStringToTimeIsValid() {
        Hotel hotel = getHotel();
        Time time = Time.valueOf("11:11:00");
        Time result = hotel.parseStringToTime("11:11");
        assertEquals(time, result);
    }

    private Hotel getHotel() {
        Hotel hotel = new Hotel();
        hotel.setName("hotel name");
        hotel.setRating(3);
        hotel.setAddress("address");
        hotel.setZip("36986");
        hotel.setCity("city");
        hotel.setPhone("123123123");
        hotel.setFax("123123123");
        hotel.setEmail("email@email.com");
        hotel.setWebsite("www.site.com");
        hotel.setDescription("description");
        hotel.setCheckIn("10:10");
        hotel.setCheckOut("11:11");
        hotel.setCountry(new Country());

        return hotel;
    }
}
