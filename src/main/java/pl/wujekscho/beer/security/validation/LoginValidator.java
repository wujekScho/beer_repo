package pl.wujekscho.beer.security.validation;

import lombok.extern.slf4j.Slf4j;
import pl.wujekscho.beer.security.service.AuthorizationService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
@ApplicationScoped
public class LoginValidator implements ConstraintValidator<Login, String> {
    @Inject
    AuthorizationService authorizationService;

    public boolean isValid(String login, ConstraintValidatorContext context) {
        return authorizationService.isLoginUnique(login);
    }
}
