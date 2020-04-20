package pl.wujekscho.beer.yeast.controller;

import io.quarkus.security.Authenticated;
import lombok.extern.slf4j.Slf4j;
import pl.wujekscho.beer.generic.controller.BaseResource;
import pl.wujekscho.beer.generic.dto.ResponseBuilder;
import pl.wujekscho.beer.yeast.dto.mapper.YeastMapper;
import pl.wujekscho.beer.yeast.entity.Yeast;

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

@Path("/yeasts")
@RequestScoped
@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
@Slf4j
public class YeastController extends BaseResource {
    @Inject
    YeastService yeastService;

    @Inject
    YeastMapper yeastMapper;

    @GET
    public Response getAll() {
        List<Yeast> yeasts = yeastService.getAllAccepted();
        return new ResponseBuilder()
                .setEntity(yeastMapper.toDtoList(yeasts))
                .buildResponse();
    }

}
