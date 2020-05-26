package pl.wujekscho.beer.time.zone.service;

import lombok.extern.slf4j.Slf4j;
import pl.wujekscho.beer.generic.exception.NoDBResultException;
import pl.wujekscho.beer.time.zone.dto.TimeZoneDto;
import pl.wujekscho.beer.time.zone.entity.TimeZone;
import pl.wujekscho.beer.time.zone.mapper.TimeZoneMapper;
import pl.wujekscho.beer.time.zone.repository.TimeZoneRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
public class TimeZoneService {
    @Inject
    TimeZoneRepository timeZoneRepository;

    @Inject
    TimeZoneMapper timeZoneMapper;

    public TimeZone getById(Long timeZoneId) {
        TimeZone byId = timeZoneRepository.findById(timeZoneId);
        if (byId == null) {
            throw new NoDBResultException("Did not found time zone of id: " + timeZoneId);
        }
        return byId;
    }

    public List<TimeZone> getAll() {
        List<TimeZone> list = timeZoneRepository.findAll().list();
        if (list.isEmpty()) {
            log.warn("No timezones found");
            throw new NoDBResultException("No timezones found");
        }
        return list;
    }

    public Map<String, List<TimeZoneDto>> getMap() {
        return getAll().stream()
                .collect(Collectors.groupingBy(TimeZone::getCountry))
                .entrySet().stream()
                .collect(Collectors.toMap(e -> new Locale("", e.getKey()).getDisplayCountry(), e -> timeZoneMapper.toDtoList(e.getValue()).stream()
                        .sorted(Comparator.comparing(TimeZoneDto::getRegion))
                        .collect(Collectors.toList()), (e1, e2) -> e1, LinkedHashMap::new))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    }
}
