package pl.wujekscho.beer.controller;

import pl.wujekscho.beer.entity.Brewing;
import pl.wujekscho.beer.service.BrewingService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/brewings")
@RequestScoped
@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BrewingController {
    @Inject
    BrewingService brewingService;

    @GET
    public List<Brewing> getAll() {
        return brewingService.getAll();
    }

    @POST
    public void save(@Valid Brewing brewing) {
        brewingService.save(brewing);
    }
}

