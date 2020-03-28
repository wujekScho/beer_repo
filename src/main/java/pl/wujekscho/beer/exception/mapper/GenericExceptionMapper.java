package pl.wujekscho.beer.exception.mapper;

import lombok.extern.slf4j.Slf4j;
import pl.wujekscho.beer.dto.ResponseBuilder;
import pl.wujekscho.beer.exception.NoDBResultException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;

@Provider
@Slf4j
public class GenericExceptionMapper implements ExceptionMapper<RuntimeException> {
    @Override
    public Response toResponse(RuntimeException exception) {
        log.error("Exception occurred", exception);

        Response.Status status = Response.Status.BAD_REQUEST;
        HashMap<String, String> errorMessages = new HashMap<>();

        if (exception instanceof NoDBResultException) {
            status = Response.Status.NOT_FOUND;
            errorMessages.put("NoDBResultException", exception.getMessage());
        } else {
            status = Response.Status.INTERNAL_SERVER_ERROR;
            errorMessages.put(exception.getClass().getSimpleName(), exception.getMessage());
        }

        return new ResponseBuilder()
                .setErrorMessages(errorMessages.isEmpty() ? null : errorMessages)
                .setStatus(status)
                .buildResponse();
    }
}
