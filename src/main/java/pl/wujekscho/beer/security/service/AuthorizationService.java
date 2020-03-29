package pl.wujekscho.beer.security.service;

import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import pl.wujekscho.beer.exception.IncorrectLoginException;
import pl.wujekscho.beer.security.dto.LoginRequest;
import pl.wujekscho.beer.security.entity.User;
import pl.wujekscho.beer.security.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Slf4j
public class AuthorizationService {

    @Inject
    UserRepository userRepository;

    public boolean isLoginUnique(String login) {
        return userRepository.find("login", login).count() == 0;
    }

    public void registerUser(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        userRepository.persist(user);
    }

    public User authenticate(LoginRequest request) {
        User user = userRepository.find("login", request.getLogin()).firstResult();
        if (user == null) {
            throw new IncorrectLoginException();
        }
        boolean valid = BCrypt.checkpw(request.getPassword(), user.getPassword());
        if (!valid) {
            throw new IncorrectLoginException();
        }
        return user;
    }
}
