package pl.wujekscho.beer.validation;

import lombok.extern.slf4j.Slf4j;
import pl.wujekscho.beer.service.BrewingService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
@ApplicationScoped
public class BrewingNameValidator implements ConstraintValidator<BrewingNameConstraint, String> {
    @Inject
    BrewingService brewingService;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return !brewingService.isBrewingNameTaken(name);
    }
}
