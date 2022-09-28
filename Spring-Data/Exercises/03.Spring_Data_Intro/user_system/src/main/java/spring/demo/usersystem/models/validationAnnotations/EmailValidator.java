package spring.demo.usersystem.models.validationAnnotations;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class EmailValidator implements ConstraintValidator<Email, String> {

    @Override
    public void initialize(Email constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        final String pattern = "^[A-Za-z0-9][A-Za-z0-9.\\-_]*(?<=[A-Za-z0-9])@(?=[A-Za-z])[A-Za-z\\-]*[A-Za-z](\\.(?=[A-Za-z])[A-Za-z\\-]*[A-Za-z])+$";
        return Pattern.matches(pattern, value);
    }
}
