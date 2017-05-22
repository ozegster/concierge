package ba.codecentric.base.validation.validator;

import ba.codecentric.base.service.FacilityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.runners.MockitoJUnitRunner;
import javax.validation.ConstraintValidatorContext;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UniqueFacilityNameValidatorTest {

    @Mock
    private FacilityService facilityService;

    @InjectMocks
    private UniqueFacilityNameValidator uniqueFacilityNameValidator;

    @Test
    public void nameExists(){
        ConstraintValidatorContext ctx = mock(ConstraintValidatorContext.class);
        when(facilityService.isExistingName("The Facility Name")).thenReturn(true);
        boolean result = uniqueFacilityNameValidator.isValid("The Facility Name", ctx );
        assertFalse(result);
    }

    @Test
    public void nameNotExists(){
        ConstraintValidatorContext ctx = mock(ConstraintValidatorContext.class);
        when(facilityService.isExistingName("The Facility Name")).thenReturn(false);
        boolean result = uniqueFacilityNameValidator.isValid("The Facility Name", ctx );
        assertTrue(result);
    }
}
