package pl.wujekscho.beer.generic.exception.mapper;

import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.api.validation.ResteasyViolationException;
import pl.wujekscho.beer.generic.dto.ResponseBuilder;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Slf4j
public class ViolationExceptionMapper implements ExceptionMapper<ResteasyViolationException> {
    @Override
    public Response toResponse(ResteasyViolationException e) {
        log.error("ResteasyViolationException occurred", e);
        return new ResponseBuilder()
                .addErrorMessage("ConstraintViolationException", "One or many constraint violations occurred.")
                .setStatus(Response.Status.BAD_REQUEST)
                .setClassViolations(e.getClassViolations())
                .setParameterViolations(e.getParameterViolations())
                .setPropertyViolations(e.getPropertyViolations())
                .setReturnValueViolations(e.getReturnValueViolations())
                .buildResponse();
    }
}
