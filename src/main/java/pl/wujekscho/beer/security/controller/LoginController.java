package pl.wujekscho.beer.security.controller;

import lombok.extern.slf4j.Slf4j;
import pl.wujekscho.beer.dto.ResponseBuilder;
import pl.wujekscho.beer.security.TokenUtils;
import pl.wujekscho.beer.security.dto.LoginRequest;
import pl.wujekscho.beer.security.service.AuthorizationService;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
@RequestScoped
@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class LoginController {
    @Inject
    AuthorizationService authorizationService;

    @GET
    @PermitAll
    public Response login(@Valid LoginRequest request) {


        String token = null;
        try {
            token = TokenUtils.generateTokenString();
        } catch (Exception e) {
            log.error("Unexpected error during login", e);
        }
        log.info("Token {}", token);
        return new ResponseBuilder()
                .setEntity(token)
                .buildResponse();
    }

}
