package pl.wujekscho.beer.security.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.wujekscho.beer.generic.exception.InvalidActivationTokenException;
import pl.wujekscho.beer.security.entity.ActivationToken;
import pl.wujekscho.beer.security.entity.User;
import pl.wujekscho.beer.security.repository.ActivationTokenRepository;
import pl.wujekscho.beer.utils.Time;

@QuarkusTest
class ActivationServiceTest {
    ActivationService activationService;

    @InjectMock
    ActivationTokenRepository tokenRepository;

    @BeforeEach
    public void setup() {
        activationService = new ActivationService(tokenRepository);
    }

    @Test
    void confirmRegistrationWhenTokenIsNotFound() {
        PanacheQuery panacheQueryMock = Mockito.mock(PanacheQuery.class);
        Mockito.when(tokenRepository.find("token", "token123")).thenReturn(panacheQueryMock);
        Mockito.when(panacheQueryMock.firstResult()).thenReturn(null);

        Assertions.assertThrows(InvalidActivationTokenException.class, () -> activationService.confirmRegistration("token123"), "Did not found activation token: token123");
    }

    @Test
    void confirmRegistrationWhenTokenIsExpired() {
        PanacheQuery panacheQueryMock = Mockito.mock(PanacheQuery.class);
        Mockito.when(tokenRepository.find("token", "token123")).thenReturn(panacheQueryMock);
        Mockito.when(panacheQueryMock.firstResult()).thenReturn(ActivationToken.builder()
                .expiryDate(Time.nowAtUtc().minusMinutes(5))
                .build());

        Assertions.assertThrows(InvalidActivationTokenException.class, () -> activationService.confirmRegistration("token123"), "Passed token is expired.");
    }

    @Test
    void confirmRegistrationWhenTokenIsProper() {
        PanacheQuery panacheQueryMock = Mockito.mock(PanacheQuery.class);
        Mockito.when(tokenRepository.find("token", "token123")).thenReturn(panacheQueryMock);
        Mockito.when(panacheQueryMock.firstResult()).thenReturn(ActivationToken.builder()
                .expiryDate(Time.nowAtUtc().plusMinutes(5))
                .user(new User())
                .build());

        Assertions.assertDoesNotThrow(() -> activationService.confirmRegistration("token123"));
    }
}