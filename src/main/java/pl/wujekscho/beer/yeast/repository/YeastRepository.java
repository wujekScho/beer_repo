package pl.wujekscho.beer.yeast.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import pl.wujekscho.beer.yeast.entity.Yeast;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class YeastRepository implements PanacheRepositoryBase<Yeast, Long> {
}
