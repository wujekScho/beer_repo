package pl.wujekscho.beer.security.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import pl.wujekscho.beer.security.entity.User;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, Long> {
}
