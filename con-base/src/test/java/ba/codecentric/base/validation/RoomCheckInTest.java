package ba.codecentric.base.validation;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.Time;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomCheckInTest {

    private static Validator validator;
    private final static Logger LOG = Logger.getLogger(RoomCheckInTest.class);

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void dateFormat() {

        Date date = new Date();


        Time time = new Time(3, 32, 20);
        date.setTime(time.getTime());
        System.out.println(date.toString());
    }
}
