package pl.wujekscho.beer.security.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LoginValidator.class)
@Documented
public @interface Login {
    String message() default "Login already taken.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
