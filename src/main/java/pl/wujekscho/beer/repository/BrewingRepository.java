package pl.wujekscho.beer.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import pl.wujekscho.beer.entity.Brewing;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BrewingRepository implements PanacheRepository<Brewing> {
}
