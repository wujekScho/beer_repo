package pl.wujekscho.beer.brewing.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.jwt.Claim;
import pl.wujekscho.beer.brewing.entity.Brewing;
import pl.wujekscho.beer.brewing.repository.BrewingRepository;
import pl.wujekscho.beer.generic.exception.NoDBResultException;
import pl.wujekscho.beer.security.entity.User;
import pl.wujekscho.beer.security.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Slf4j
@Transactional
@NoArgsConstructor
@AllArgsConstructor
public class BrewingService {
    @Inject
    BrewingRepository brewingRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    @Claim("userId")
    Long userId;

    public List<Brewing> getAll() {
        List<Brewing> list = brewingRepository
                .find("user_id", userId)
                .list();
        if (list.isEmpty()) {
            log.warn("No brewing found.");
            throw new NoDBResultException();
        }
        return list;
    }

    public Brewing save(Brewing brewing) {
        User creator = userRepository
                .findById(userId);
        if (creator == null) {
            throw new NoDBResultException("Did not found user of id: {}" + userId);
        }
        brewing.setUser(creator);
        brewingRepository.persist(brewing);
        log.info("Successfully persisted brewing: {}", brewing);
        return brewing;
    }

    public Brewing getById(Long brewingId) {
        return brewingRepository
                .find("id = ?1 and user_id = ?2", brewingId, userId)
                .firstResultOptional()
                .orElseThrow(NoDBResultException::new);
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
