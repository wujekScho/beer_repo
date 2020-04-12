package pl.wujekscho.beer.generic.controller;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.ZoneId;

@Slf4j
@ApplicationScoped
public class BaseResource {
    @Inject
    JsonWebToken token;

    @Inject
    @Claim("zone")
    String zone;

    public ZoneId getUserTimezone() {
        return ZoneId.of(zone);
    }

}
