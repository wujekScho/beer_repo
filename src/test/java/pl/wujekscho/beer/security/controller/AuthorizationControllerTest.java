package pl.wujekscho.beer.security.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.api.validation.ResteasyConstraintViolation;
import org.junit.jupiter.api.Test;
import pl.wujekscho.beer.dto.ResponseBuilder;
import pl.wujekscho.beer.security.dto.LoginRequest;
import service.TestAuthorizationService;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@Slf4j
class AuthorizationControllerTest {
    @Inject
    TestAuthorizationService authorizationService;

    @Test
    public void testLoginEndpoint() {
        LoginRequest loginRequest = LoginRequest.builder()
                .login("test@test.org")
                .password("secret@asda")
                .build();

        ResponseBuilder responseBuilder = given()
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .when().post("/authorization/login")
                .then()
                .statusCode(400)
                .extract()
                .as(ResponseBuilder.class);

        List<ResteasyConstraintViolation> parameterViolations = responseBuilder.getParameterViolations();

        Set<String> messages = parameterViolations.stream()
                .map(ResteasyConstraintViolation::getMessage)
                .collect(Collectors.toSet());

        assertTrue(messages.containsAll(Arrays.asList("Login already taken.",
                "Password must contain at least 8 characters, one letter, one number and one special character.")));
    }

}