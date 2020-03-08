package pl.wujekscho.beer.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pl.wujekscho.beer.dto.BrewingDto;
import pl.wujekscho.beer.dto.ResponseBuilder;
import pl.wujekscho.beer.dto.mapper.BrewingMapper;
import pl.wujekscho.beer.entity.Brewing;
import pl.wujekscho.beer.service.BrewingService;

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

    @Test
    public void testSaveEndpoint() {
        BrewingDto dto = getTestBrewing();

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/brewings")
                .then()
                .statusCode(201);
    }

    @Test
    public void testSaveEndpointForNotUniqueBrewingName() {
        BrewingDto dto = getTestBrewing();
        brewingService.save(brewingMapper.toEntity(dto));

        ResponseBuilder response = given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/brewings")
                .then()
                .statusCode(400)
                .extract()
                .as(ResponseBuilder.class);

        assertNotNull(response.getErrorMessages().get("ConstraintViolationException"));
    }

    @Test
    public void testDeleteEndpoint() {
        BrewingDto dto = getTestBrewing();
        brewingService.save(brewingMapper.toEntity(dto));
        Long brewingId = brewingService.getByName("Test name").getId();

        given()
                .pathParam("brewingId", brewingId)
                .when().delete("/brewings/{brewingId}")
                .then()
                .statusCode(204);
    }

    private BrewingDto getTestBrewing() {
        return BrewingDto.builder()
                .name("Test name")
                .gravity(10.0)
                .volume(20.0)
                .style("Bitter")
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