package pl.wujekscho.beer.brewing.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pl.wujekscho.beer.brewing.dto.BrewingRequest;
import pl.wujekscho.beer.brewing.dto.mapper.BrewingMapper;
import pl.wujekscho.beer.brewing.entity.Brewing;
import pl.wujekscho.beer.brewing.service.BrewingService;
import pl.wujekscho.beer.generic.dto.ResponseBuilder;
import service.TestAuthorizationService;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
@Slf4j
class BrewingControllerTest {

    @Inject
    BrewingService brewingService;

    @Inject
    BrewingMapper brewingMapper;

    @Inject
    TestAuthorizationService authorizationService;

    @Test
    public void testSaveEndpoint() {
        BrewingRequest dto = getTestBrewing();

        given()
                .header(authorizationService.getAuthHeader())
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/api/brewings")
                .then()
                .statusCode(201);
    }

    @Test
    public void testSaveEndpointForNotUniqueBrewingName() {
        BrewingRequest dto = getTestBrewing();
        brewingService.save(brewingMapper.fromRequestToEntity(dto));

        ResponseBuilder response = given()
                .header(authorizationService.getAuthHeader())
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/api/brewings")
                .then()
                .statusCode(400)
                .extract()
                .as(ResponseBuilder.class);

        assertNotNull(response.getErrorMessages().get("ConstraintViolationException"));
    }

    @Test
    public void testDeleteEndpoint() {
        BrewingRequest dto = getTestBrewing();
        brewingService.save(brewingMapper.fromRequestToEntity(dto));
        Long brewingId = brewingService.getByName("Test name").getId();

        given()
                .header(authorizationService.getAuthHeader())
                .pathParam("brewingId", brewingId)
                .when().delete("/api/brewings/{brewingId}")
                .then()
                .statusCode(204);
    }

    private BrewingRequest getTestBrewing() {
        return BrewingRequest.builder()
                .name("Test name")
                .gravity(10.0)
                .volume(20.0)
                .style("Bitter")
                .yeastId(1L)
                .build();
    }

    @AfterEach
    public void cleanTestData() {
        Brewing testBrewing = brewingService.getByName("Test name");
        if (testBrewing != null) {
            brewingService.delete(testBrewing.getId());
        }
    }
}