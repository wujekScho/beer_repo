package pl.wujekscho.beer.brewing.controller;

import io.quarkus.security.Authenticated;
import lombok.extern.slf4j.Slf4j;
import pl.wujekscho.beer.brewing.dto.BrewingDto;
import pl.wujekscho.beer.brewing.dto.BrewingRequest;
import pl.wujekscho.beer.brewing.dto.mapper.BrewingMapper;
import pl.wujekscho.beer.brewing.entity.Brewing;
import pl.wujekscho.beer.brewing.service.BrewingService;
import pl.wujekscho.beer.generic.controller.BaseResource;
import pl.wujekscho.beer.generic.dto.ResponseBuilder;

import javax.enterprise.context.RequestScoped;
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
@RequestScoped
@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
@Slf4j
public class BrewingController extends BaseResource {
    @Inject
    BrewingService brewingService;

    @Inject
    BrewingMapper brewingMapper;

    @GET
    public Response getAll() {
        List<BrewingDto> dtos = brewingService.getAll().stream()
                .map(brewing -> brewingMapper.toDto(brewing, getUserTimezone()))
                .collect(Collectors.toList());
        return new ResponseBuilder()
                .setEntity(dtos)
                .buildResponse();
    }

    @GET
    @Path("/{brewingId}")
    public Response getById(@PathParam("brewingId") @Positive Long brewingId) {
        BrewingDto dto = brewingMapper.toDto(brewingService.getById(brewingId), getUserTimezone());
        return new ResponseBuilder()
                .setEntity(dto)
                .buildResponse();
    }

    @POST
    public Response save(@Valid BrewingRequest dto) {
        Brewing entity = brewingMapper.fromRequestToEntity(dto);
        Brewing brewing = brewingService.save(entity);
        return new ResponseBuilder()
                .setStatus(Response.Status.CREATED)
                .setEntity(brewingMapper.toDto(brewing, getUserTimezone()))
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