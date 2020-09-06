package pl.wujekscho.beer.security.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.wujekscho.beer.generic.exception.InvalidActivationTokenException;
import pl.wujekscho.beer.security.repository.ActivationTokenRepository;

@QuarkusTest
class ActivationServiceTest {
    ActivationService activationService;

    @InjectMock
    ActivationTokenRepository tokenRepository;

    @BeforeEach
    public void setup() {
        PanacheQuery panacheQueryMock = Mockito.mock(PanacheQuery.class);
        Mockito.when(tokenRepository.find("token", "null")).thenReturn(panacheQueryMock);
        Mockito.when(panacheQueryMock.firstResult()).thenReturn(null);
        activationService = new ActivationService(tokenRepository);
    }

    @Test
    void confirmRegistrationWhenTokenIsNotFound() {
        Assertions.assertThrows(InvalidActivationTokenException.class, () -> activationService.confirmRegistration("null"));
    }

}