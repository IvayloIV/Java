package spring.demo.usersystem.models.validationAnnotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {

    String message() default "Invalid password!";

    Class<?> [] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int minLength();

    int maxLength();

    boolean containsDigit() default false;

    boolean containsLowercase() default false;

    boolean containsUppercase() default false;

    boolean containsSpecialSymbol() default false;
}
