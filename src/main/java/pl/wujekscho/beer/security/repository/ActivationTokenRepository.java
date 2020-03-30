package pl.wujekscho.beer.security.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import pl.wujekscho.beer.security.entity.ActivationToken;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActivationTokenRepository implements PanacheRepositoryBase<ActivationToken, Long> {
}
