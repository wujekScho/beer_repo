package pl.wujekscho.beer.yeast.controller;

import lombok.extern.slf4j.Slf4j;
import pl.wujekscho.beer.generic.exception.NoDBResultException;
import pl.wujekscho.beer.yeast.entity.Yeast;
import pl.wujekscho.beer.yeast.repository.YeastRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@Slf4j
public class YeastService {
    @Inject
    YeastRepository yeastRepository;

    public List<Yeast> getAllAccepted() {
        List<Yeast> list = yeastRepository
                .find("pending", false)
                .list();
        if (list.isEmpty()) {
            log.warn("No accepted yeast found.");
            throw new NoDBResultException("No accepted yeast found.");
        }
        return list;
    }
}
