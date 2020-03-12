package pl.wujekscho.beer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jboss.resteasy.api.validation.ResteasyConstraintViolation;

import javax.json.bind.annotation.JsonbTransient;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class ResponseBuilder {
    private Response.Status status = Response.Status.OK;
    private String created = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private Map<String, String> errorMessages;
    private List<ResteasyConstraintViolation> propertyViolations;
    private List<ResteasyConstraintViolation> classViolations;
    private List<ResteasyConstraintViolation> parameterViolations;
    private List<ResteasyConstraintViolation> returnValueViolations;
    private Object entity;

    public ResponseBuilder setStatus(Response.Status status) {
        this.status = status;
        return this;
    }

    public ResponseBuilder setErrorMessages(Map<String, String> errorMessages) {
        if (!errorMessages.isEmpty()) {
            this.errorMessages = errorMessages;
        }
        return this;
    }

    public ResponseBuilder setEntity(Object entity) {
        this.entity = entity;
        return this;
    }

    public ResponseBuilder setPropertyViolations(List<ResteasyConstraintViolation> propertyViolations) {
        if (!propertyViolations.isEmpty()) {
            this.propertyViolations = propertyViolations;
        }
        return this;
    }

    public ResponseBuilder setClassViolations(List<ResteasyConstraintViolation> classViolations) {
        if (!classViolations.isEmpty()) {
            this.classViolations = classViolations;
        }
        return this;
    }

    public ResponseBuilder setParameterViolations(List<ResteasyConstraintViolation> parameterViolations) {
        if (!parameterViolations.isEmpty()) {
            this.parameterViolations = parameterViolations;
        }
        return this;
    }

    public ResponseBuilder setReturnValueViolations(List<ResteasyConstraintViolation> returnValueViolations) {
        if (!returnValueViolations.isEmpty()) {
            this.returnValueViolations = returnValueViolations;
        }
        return this;
    }

    @JsonbTransient
    public ResponseBuilder addErrorMessage(String error, String message) {
        if (errorMessages == null) {
            this.errorMessages = new LinkedHashMap<>();
        }
        this.errorMessages.put(error, message);
        return this;
    }

    @JsonbTransient
    public Response buildResponse() {
        return Response
                .status(this.status.getStatusCode())
                .entity(this)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
