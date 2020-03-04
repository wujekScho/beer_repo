package pl.wujekscho.beer.dto;

import lombok.Getter;
import lombok.Setter;
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
public class RestResponse { //todo zastosować wzorzec budowniczego
    Response.Status status = Response.Status.OK;
    String created = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    Map<String, String> errorMessages;
    List<ResteasyConstraintViolation> propertyViolations;
    List<ResteasyConstraintViolation> classViolations;
    List<ResteasyConstraintViolation> parameterViolations;
    List<ResteasyConstraintViolation> returnValueViolations;
    Object entity;

    public RestResponse setStatus(Response.Status status) {
        this.status = status;
        return this;
    }

    public RestResponse setErrorMessages(Map<String, String> errorMessages) {
        if (!errorMessages.isEmpty()) {
            this.errorMessages = errorMessages;
        }
        return this;
    }

    public RestResponse setEntity(Object entity) {
        this.entity = entity;
        return this;
    }

    public RestResponse setPropertyViolations(List<ResteasyConstraintViolation> propertyViolations) {
        if (!propertyViolations.isEmpty()) {
            this.propertyViolations = propertyViolations;
        }
        return this;
    }

    public RestResponse setClassViolations(List<ResteasyConstraintViolation> classViolations) {
        if (!classViolations.isEmpty()) {
            this.classViolations = classViolations;
        }
        return this;
    }

    public RestResponse setParameterViolations(List<ResteasyConstraintViolation> parameterViolations) {
        if (!parameterViolations.isEmpty()) {
            this.parameterViolations = parameterViolations;
        }
        return this;
    }

    public RestResponse setReturnValueViolations(List<ResteasyConstraintViolation> returnValueViolations) {
        if (!returnValueViolations.isEmpty()) {
            this.returnValueViolations = returnValueViolations;
        }
        return this;
    }

    @JsonbTransient
    public RestResponse addErrorMessage(String error, String message) {
        if (errorMessages == null) {
            this.errorMessages = new LinkedHashMap<>();
        }
        this.errorMessages.put(error, message);
        return this;
    }

    @JsonbTransient
    public Response getResponse() {
        return Response
                .status(this.status.getStatusCode())
                .entity(this)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
