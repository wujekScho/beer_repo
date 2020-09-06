package pl.wujekscho.beer.brewing.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.wujekscho.beer.brewing.entity.Brewing;
import pl.wujekscho.beer.brewing.repository.BrewingRepository;
import pl.wujekscho.beer.generic.exception.NoDBResultException;
import pl.wujekscho.beer.security.repository.UserRepository;

import java.util.Collections;

import static org.mockito.Mockito.when;

@QuarkusTest
class BrewingServiceTest {
    BrewingService brewingService;

    @InjectMock
    BrewingRepository brewingRepository;

    @InjectMock
    UserRepository userRepository;

    @BeforeEach
    public void setup() {
        brewingService = new BrewingService(brewingRepository, userRepository, 1L);
        PanacheQuery<Brewing> queryMock = Mockito.mock(PanacheQuery.class);
        when(brewingRepository.find("user_id", 1L)).thenReturn(queryMock);
        when(queryMock.list()).thenReturn(Collections.emptyList());
    }

    @Test
    void getAll() {
        Assertions.assertThrows(NoDBResultException.class, () -> brewingService.getAll());
    }
}