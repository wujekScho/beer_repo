package pl.wujekscho.beer.brewing.validation;

import lombok.extern.slf4j.Slf4j;
import pl.wujekscho.beer.brewing.service.BrewingService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
@ApplicationScoped
public class BrewingNameValidator implements ConstraintValidator<BrewingName, String> {
    @Inject
    BrewingService brewingService;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return !brewingService.isBrewingNameTaken(name);
    }
}
