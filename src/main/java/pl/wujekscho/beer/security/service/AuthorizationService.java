package pl.wujekscho.beer.security.service;

import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import pl.wujekscho.beer.email.dto.EmailEvent;
import pl.wujekscho.beer.email.entity.EmailType;
import pl.wujekscho.beer.generic.exception.IncorrectLoginException;
import pl.wujekscho.beer.generic.exception.NotActivatedUserException;
import pl.wujekscho.beer.security.dto.LoginRequest;
import pl.wujekscho.beer.security.entity.Role;
import pl.wujekscho.beer.security.entity.User;
import pl.wujekscho.beer.security.repository.UserRepository;
import pl.wujekscho.beer.time.zone.service.TimeZoneService;
import pl.wujekscho.beer.utils.Time;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.validation.constraints.Positive;
import java.util.Collections;
import java.util.HashSet;

@ApplicationScoped
@Slf4j
public class AuthorizationService {

    @Inject
    ActivationService activationService;

    @Inject
    TimeZoneService timeZoneService;

    @Inject
    UserRepository userRepository;

    @Inject
    Event<EmailEvent> emailEvent;

    public boolean isLoginUnique(String login) {
        return userRepository.find("login", login).count() == 0;
    }

    public User registerUser(String login, String password, @Positive Long timeZoneId) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        user.setRoles(new HashSet<>(Collections.singletonList(Role.REGULAR_USER)));
        user.setActivated(false);
        user.setTimeZone(timeZoneService.getById(timeZoneId));
        userRepository.persist(user);

        String token = activationService.generateToken(user);

        emailEvent.fire(EmailEvent.builder()
                .creator(login)
                .timestamp(Time.nowAtUtc())
                .type(EmailType.REGISTRATION)
                .recipient(login)
                .token(token)
                .build());
        return user;
    }

    public User authenticate(LoginRequest request) {
        User user = userRepository.find("login", request.getLogin()).firstResult();
        if (user == null) {
            throw new IncorrectLoginException();
        }
        if (!user.isActivated()) {
            throw new NotActivatedUserException();
        }
        boolean valid = BCrypt.checkpw(request.getPassword(), user.getPassword());
        if (!valid) {
            throw new IncorrectLoginException();
        }
        return user;
    }
}
