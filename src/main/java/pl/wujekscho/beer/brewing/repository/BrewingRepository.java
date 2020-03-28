package pl.wujekscho.beer.brewing.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import pl.wujekscho.beer.brewing.entity.Brewing;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BrewingRepository implements PanacheRepositoryBase<Brewing, Long> {
}
