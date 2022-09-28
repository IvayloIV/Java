package spring.demo.usersystem.models.validationAnnotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

    private Boolean containsDigit;
    private Boolean containsLowercase;
    private Boolean containsUppercase;
    private Boolean containsSpecialSymbol;
    private int minLength;
    private int maxLength;

    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {
        containsDigit = constraintAnnotation.containsDigit();
        containsLowercase = constraintAnnotation.containsLowercase();
        containsUppercase = constraintAnnotation.containsUppercase();
        containsSpecialSymbol = constraintAnnotation.containsSpecialSymbol();
        minLength = constraintAnnotation.minLength();
        maxLength = constraintAnnotation.maxLength();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (minLength > value.length()) {
            return false;
        }

        if (maxLength < value.length()) {
            return false;
        }

        if (containsDigit && !Pattern.compile("[0-9]").matcher(value).find()) {
            return false;
        }

        if (containsLowercase && !Pattern.compile("[a-z]").matcher(value).find()) {
            return false;
        }

        if (containsUppercase && !Pattern.compile("[A-Z]").matcher(value).find()) {
            return false;
        }

        if (containsSpecialSymbol && !Pattern.compile("[!@#$%^&*()_+<>?]").matcher(value).find()) {
            return false;
        }

        return true;
    }
}
