package pl.wujekscho.beer.service;

import pl.wujekscho.beer.entity.Brewing;
import pl.wujekscho.beer.exception.NoDBResultException;
import pl.wujekscho.beer.repository.BrewingRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class BrewingService {
    @Inject
    BrewingRepository brewingRepository;

    public List<Brewing> getAll() {
        List<Brewing> list = brewingRepository.findAll().list();
        if (list.isEmpty()) {
            throw new NoDBResultException();
        }
        return list;
    }

    public void save(Brewing brewing) {
        brewingRepository.persist(brewing);
    }

    public Brewing getById(Long brewingId) {
        Brewing byId = brewingRepository.findById(brewingId);
        if (byId == null) {
            throw new NoDBResultException();
        }
        return byId;
    }

    public boolean isBrewingNameTaken(String name) {
        return brewingRepository.find("name", name).firstResult() != null;
    }

    public List<Brewing> getByName(String name) {
        return brewingRepository.find("name", name).list();
    }
}
