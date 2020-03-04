package pl.wujekscho.beer.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BrewingNameValidator.class)
@Documented
public @interface BrewingNameConstraint {
    String message() default "Brewing with passed name already exist.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
