package pl.wujekscho.beer.security.controller;


import org.eclipse.microprofile.jwt.JsonWebToken;
import pl.wujekscho.beer.dto.ResponseBuilder;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

@Path("/security")
@RequestScoped
@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SecurityController {

    @Inject
    JsonWebToken jwt;

    @GET
    @RolesAllowed("admin")
    public Response getPrincipalInfo(@Context SecurityContext ctx) {
        Principal caller = ctx.getUserPrincipal();
        String name = caller == null ? "anonymous" : caller.getName();
        boolean hasJWT = jwt.getClaimNames() != null;
        String helloReply = String.format("hello + %s, isSecure: %s, authScheme: %s, hasJWT: %s", name, ctx.isSecure(), ctx.getAuthenticationScheme(), hasJWT);
        return new ResponseBuilder()
                .setEntity(helloReply)
                .buildResponse();
    }

}
