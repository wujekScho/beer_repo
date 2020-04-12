package pl.wujekscho.beer.time.zone.service;

import lombok.extern.slf4j.Slf4j;
import pl.wujekscho.beer.generic.exception.NoDBResultException;
import pl.wujekscho.beer.time.zone.entity.TimeZone;
import pl.wujekscho.beer.time.zone.repository.TimeZoneRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
public class TimeZoneService {

    @Inject
    TimeZoneRepository timeZoneRepository;


    public TimeZone getById(Long timeZoneId) {
        TimeZone byId = timeZoneRepository.findById(timeZoneId);
        if (byId == null) {
            throw new NoDBResultException("Did not found time zone of id: " + timeZoneId);
        }
        return byId;
    }
}
