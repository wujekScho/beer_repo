package pl.wujekscho.beer.time.zone.controller;

import io.quarkus.security.Authenticated;
import lombok.extern.slf4j.Slf4j;
import pl.wujekscho.beer.generic.dto.ResponseBuilder;
import pl.wujekscho.beer.time.zone.dto.TimeZoneDto;
import pl.wujekscho.beer.time.zone.service.TimeZoneService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/time-zones")
@RequestScoped
@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
@Slf4j
public class TimeZoneController {
    @Inject
    TimeZoneService timeZoneService;

    @GET
    public Response getAll() {
        Map<String, List<TimeZoneDto>> timeZonesMap = timeZoneService.getMap();
        return new ResponseBuilder()
                .setEntity(timeZonesMap)
                .buildResponse();
    }
}
