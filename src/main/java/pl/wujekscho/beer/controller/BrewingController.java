package pl.wujekscho.beer.controller;

import pl.wujekscho.beer.dto.BrewingDto;
import pl.wujekscho.beer.dto.RestResponse;
import pl.wujekscho.beer.dto.mapper.BrewingMapper;
import pl.wujekscho.beer.entity.Brewing;
import pl.wujekscho.beer.service.BrewingService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
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
        return new RestResponse()
                .setEntity(dtos)
                .getResponse();
    }

    @GET
    @Path("/{brewingId}")
    public Response getById(@PathParam("brewingId") Long brewingId) {
        BrewingDto dto = brewingMapper.toDto(brewingService.getById(brewingId));
        return new RestResponse()
                .setEntity(dto)
                .getResponse();
    }

    @POST
    public Response save(@Valid BrewingDto dto) {
        Brewing entity = brewingMapper.toEntity(dto);
        brewingService.save(entity);
        return new RestResponse()
                .setStatus(Response.Status.CREATED)
                .setEntity(entity)
                .getResponse();
    }
}