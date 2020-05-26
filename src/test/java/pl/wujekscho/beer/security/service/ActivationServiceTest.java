package pl.wujekscho.beer.security.service;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.wujekscho.beer.generic.exception.InvalidActivationTokenException;
import pl.wujekscho.beer.security.repository.ActivationTokenRepository;

@QuarkusTest
public class ActivationServiceTest {
    @InjectMock
    ActivationTokenRepository tokenRepository;

//    @BeforeEach
//    public void setup() {
//        Mockito.when(tokenRepository.find("token", "expired").firstResult()).thenReturn(ActivationToken.builder()
//                .token("expired")
//                .expiryDate(LocalDateTime.now().minusMinutes(5))
//                .build());
//    }

    @Test
    public void confirmRegistrationWhenTokenIsNotFound() {
        Mockito.when(tokenRepository.find("token", "null").firstResult()).thenReturn(null);
        ActivationService activationService = new ActivationService(tokenRepository);
        Assertions.assertThrows(InvalidActivationTokenException.class, () -> activationService.confirmRegistration("null"));
    }

}