package pl.wujekscho.beer.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import pl.wujekscho.beer.entity.Brewing;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BrewingRepository implements PanacheRepositoryBase<Brewing, Long> {
}
