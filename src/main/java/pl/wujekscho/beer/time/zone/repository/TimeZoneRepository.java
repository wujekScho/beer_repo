package pl.wujekscho.beer.time.zone.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import pl.wujekscho.beer.time.zone.entity.TimeZone;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TimeZoneRepository implements PanacheRepositoryBase<TimeZone, Long> {
}
