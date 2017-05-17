package ba.codecentric.base.validation.validator;

import ba.codecentric.base.service.RoomTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UniqueValidatorTest {

    @Mock
    private RoomTypeService roomTypeService;

    @InjectMocks
    private UniqueValidator uniqueValidator;

    @Test
    public void nameExists(){
        ConstraintValidatorContext ctx = Mockito.mock(ConstraintValidatorContext.class);
        when(roomTypeService.isExistingName("The room")).thenReturn(true);
        boolean result = uniqueValidator.isValid("The room", ctx );
        assertFalse(result);
    }

    @Test
    public void nameNotExists(){
        ConstraintValidatorContext ctx = Mockito.mock(ConstraintValidatorContext.class);
        when(roomTypeService.isExistingName("The room")).thenReturn(false);
        boolean result = uniqueValidator.isValid("The room", ctx );
        assertTrue(result);
    }

}
