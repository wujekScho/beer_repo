package pl.wujekscho.beer.brewing.service;

import lombok.extern.slf4j.Slf4j;
import pl.wujekscho.beer.brewing.entity.Brewing;
import pl.wujekscho.beer.brewing.repository.BrewingRepository;
import pl.wujekscho.beer.exception.NoDBResultException;
import pl.wujekscho.beer.security.entity.User;
import pl.wujekscho.beer.security.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Slf4j
@Transactional
public class BrewingService {
    @Inject
    BrewingRepository brewingRepository;

    @Inject
    UserRepository userRepository;

    public List<Brewing> getAll() {
        List<Brewing> list = brewingRepository.findAll().list();
        if (list.isEmpty()) {
            log.warn("No brewing found.");
            throw new NoDBResultException();
        }
        return list;
    }

    public Brewing save(Brewing brewing, Long userId) {
        User creator = userRepository.findById(userId);
        brewing.setUser(creator);
        brewingRepository.persist(brewing);
        log.info("Successfully persisted brewing: {}", brewing);
        return brewing;
    }

    public Brewing getById(Long brewingId) {
        Brewing byId = brewingRepository.findById(brewingId);
        if (byId == null) {
            log.warn("No brewing found of id: {}", brewingId);
            throw new NoDBResultException();
        }
        return byId;
    }

    public boolean isBrewingNameTaken(String name) {
        return brewingRepository.find("name", name).firstResult() != null;
    }

    public Brewing getByName(String name) {
        return brewingRepository.find("name", name).firstResult();
    }

    public void delete(Long brewingId) {
        Brewing brewing = getById(brewingId);
        brewingRepository.delete(brewing);
        log.warn("Deleted brewing of id: {}", brewingId);
    }
}
