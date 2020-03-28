package pl.wujekscho.beer.brewing.controller;

import pl.wujekscho.beer.brewing.dto.BrewingDto;
import pl.wujekscho.beer.brewing.dto.mapper.BrewingMapper;
import pl.wujekscho.beer.brewing.entity.Brewing;
import pl.wujekscho.beer.brewing.service.BrewingService;
import pl.wujekscho.beer.dto.ResponseBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/brewings")
@ApplicationScoped
@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BrewingController {
    @Inject
    BrewingService brewingService;

    @Inject
    BrewingMapper brewingMapper;

    @GET
    public Response getAll() {
        List<BrewingDto> dtos = brewingService.getAll().stream()
                .map(brewing -> brewingMapper.toDto(brewing))
                .collect(Collectors.toList());
        return new ResponseBuilder()
                .setEntity(dtos)
                .buildResponse();
    }

    @GET
    @Path("/{brewingId}")
    public Response getById(@PathParam("brewingId") @Positive Long brewingId) {
        BrewingDto dto = brewingMapper.toDto(brewingService.getById(brewingId));
        return new ResponseBuilder()
                .setEntity(dto)
                .buildResponse();
    }

    @POST
    public Response save(@Valid BrewingDto dto) {
        Brewing entity = brewingMapper.toEntity(dto);
        Brewing brewing = brewingService.save(entity);
        return new ResponseBuilder()
                .setStatus(Response.Status.CREATED)
                .setEntity(brewing)
                .buildResponse();
    }

    @DELETE
    @Path("/{brewingId}")
    public Response delete(@PathParam("brewingId") @Positive Long brewingId) {
        brewingService.delete(brewingId);
        return new ResponseBuilder()
                .setStatus(Response.Status.NO_CONTENT)
                .buildResponse();
    }
}