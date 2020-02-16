package pl.wujekscho.beer.service;

import pl.wujekscho.beer.entity.Brewing;
import pl.wujekscho.beer.repository.BrewingRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class BrewingService {
    @Inject
    BrewingRepository brewingRepository;

    public List<Brewing> getAll() {
        return brewingRepository.findAll().list();
    }

    public void save(Brewing brewing) {
        brewingRepository.persist(brewing);
    }
}
