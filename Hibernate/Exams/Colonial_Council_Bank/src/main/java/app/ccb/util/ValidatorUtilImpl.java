package app.ccb.util;

import app.ccb.util.base.ValidatorUtil;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidatorUtilImpl implements ValidatorUtil {

    private final Validator validator;

    public ValidatorUtilImpl() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public <T> boolean isValid(T item) {
        return this.validator.validate(item).size() == 0;
    }
}
