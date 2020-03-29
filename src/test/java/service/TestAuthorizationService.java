package service;

import io.restassured.http.Header;
import lombok.extern.slf4j.Slf4j;
import pl.wujekscho.beer.security.TokenUtils;
import pl.wujekscho.beer.security.entity.User;
import pl.wujekscho.beer.security.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Slf4j
public class TestAuthorizationService {

    @Inject
    UserRepository userRepository;

    public Header getAuthHeader() {
        String token = null;
        try {
            User authenticated = userRepository.find("login", "test@test.org").firstResult();
            token = TokenUtils.generateTokenString(authenticated);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Header("Authorization", "Bearer " + token);
    }
}
