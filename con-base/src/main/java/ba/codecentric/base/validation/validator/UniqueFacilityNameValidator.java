package ba.codecentric.base.validation.validator;

import ba.codecentric.base.service.FacilityService;
import ba.codecentric.base.validation.annotation.UniqueFacilityName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueFacilityNameValidator implements ConstraintValidator<UniqueFacilityName, String> {

    private FacilityService facilityService;

    public UniqueFacilityNameValidator() {}

    @Autowired
    public UniqueFacilityNameValidator(FacilityService facilityService){
        this.facilityService = facilityService;
    }

    @Override
    public void initialize(UniqueFacilityName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !facilityService.isExistingName(value);
    }

}
