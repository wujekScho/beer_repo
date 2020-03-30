package pl.wujekscho.beer.security.service;

import lombok.extern.slf4j.Slf4j;
import pl.wujekscho.beer.exception.InvalidActivationTokenException;
import pl.wujekscho.beer.security.entity.ActivationToken;
import pl.wujekscho.beer.security.entity.User;
import pl.wujekscho.beer.security.repository.ActivationTokenRepository;
import pl.wujekscho.beer.security.repository.UserRepository;
import pl.wujekscho.beer.utils.Time;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@Slf4j
@ApplicationScoped
public class ActivationService {
    @Inject
    ActivationTokenRepository tokenRepository;

    @Inject
    UserRepository userRepository;

    public String generateToken(User user) {
        ActivationToken token = ActivationToken.builder()
                .expiryDate(Time.nowAtUtc().plusDays(1))
                .user(user)
                .token(UUID.randomUUID().toString())
                .build();

        tokenRepository.persist(token);

        return token.getToken();
    }

    public void confirmRegistration(String token) {
        ActivationToken activationToken = tokenRepository.find("token", token).firstResult();

        if (activationToken == null) {
            throw new InvalidActivationTokenException("Did not found activation token: " + token);
        }
        if (isExpired(activationToken)) {
            throw new InvalidActivationTokenException("Passed token is expired.");
        }

        User user = activationToken.getUser();
        user.setActivated(true);
        log.info("Successfully activated users account: {}", user.login);
    }

    private boolean isExpired(ActivationToken activationToken) {
        return activationToken.getExpiryDate().isBefore(Time.nowAtUtc());
    }
}
