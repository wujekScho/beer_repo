package pl.wujekscho.beer.exception.mapper;

import lombok.extern.slf4j.Slf4j;
import pl.wujekscho.beer.dto.ResponseBuilder;

import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Slf4j
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {
    @Override
    public Response toResponse(ValidationException e) {
        log.error("ValidationException occurred", e);
        return new ResponseBuilder()
                .addErrorMessage(e.getClass().getSimpleName(), e.getMessage())
                .setStatus(Response.Status.INTERNAL_SERVER_ERROR)
                .buildResponse();
    }
}
