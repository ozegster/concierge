package ba.codecentric.base.validation.validator;

import ba.codecentric.base.service.RoomTypeService;
import ba.codecentric.base.validation.annotation.Unique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueValidator implements ConstraintValidator<Unique, String> {

    private RoomTypeService roomTypeService;

    public UniqueValidator() {
    }

    @Autowired
    public UniqueValidator(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @Override
    public void initialize(Unique constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !roomTypeService.isExistingName(value);
    }

}
