package com.softuni.virus.util.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class ReleaseDateValidator implements ConstraintValidator<ReleaseDate, Date> {

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        Date today = new Date();
        return date != null && date.before(today);
    }
}
