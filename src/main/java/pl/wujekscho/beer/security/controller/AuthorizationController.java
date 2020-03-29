package pl.wujekscho.beer.security.controller;

import io.quarkus.security.Authenticated;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.jwt.JsonWebToken;
import pl.wujekscho.beer.dto.ResponseBuilder;
import pl.wujekscho.beer.security.TokenUtils;
import pl.wujekscho.beer.security.dto.LoginRequest;
import pl.wujekscho.beer.security.dto.RegistrationRequest;
import pl.wujekscho.beer.security.entity.User;
import pl.wujekscho.beer.security.service.AuthorizationService;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authorization")
@RequestScoped
@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class AuthorizationController {
    @Inject
    JsonWebToken jwt;

    @Inject
    AuthorizationService authorizationService;

    @POST
    @Path("/login")
    @PermitAll
    public Response login(@Valid LoginRequest request) throws Exception {
        User authenticated = authorizationService.authenticate(request);
        String token = TokenUtils.generateTokenString(authenticated);
        return new ResponseBuilder()
                .setEntity(token)
                .buildResponse();
    }

    @POST
    @Path("/register")
    @PermitAll
    public Response register(@Valid RegistrationRequest request) {
        authorizationService.registerUser(request.getLogin(), request.getPassword());
        return new ResponseBuilder()
                .setStatus(Response.Status.CREATED)
                .buildResponse();
    }

    @GET
    @Path("/refresh")
    @Authenticated
    public Response refreshToken() throws Exception {
        return new ResponseBuilder()
                .setEntity(TokenUtils.refreshToken(jwt))
                .buildResponse();
    }
}
