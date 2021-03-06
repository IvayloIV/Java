package alararestaurant.util;

import alararestaurant.util.base.ValidatorUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValidatorUtilImpl implements ValidatorUtil {

    private final Validator validator;

    public ValidatorUtilImpl() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public <T> boolean isValid(T item) {
        return this.validator.validate(item).size() == 0;
    }
}
