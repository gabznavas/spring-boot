package io.github.gabznavas.Book.API.integrationtests.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.gabznavas.Book.API.config.TestConfigs;
import io.github.gabznavas.Book.API.data.dto.v1.PersonDTO;
import io.github.gabznavas.Book.API.integrationtests.testcontainers.AbstractIntegrationTest;
import io.github.gabznavas.Book.API.mapper.mocks.MockPerson;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonIntegrationTest extends AbstractIntegrationTest {

    private static MockPerson input;

    private static ObjectMapper objectMapper;

    private static PersonDTO personDto;

    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        input = new MockPerson();
    }

    @Test
    @Order(1)
    void shouldCreateAPerson() throws JsonProcessingException {
        PersonDTO body = input.mockDTO();

        String content = given()
                .basePath("/api/person/v1")
                .port(TestConfigs.SERVER_PORT)
                .filter(new RequestLoggingFilter(LogDetail.ALL))
                .filter(new ResponseLoggingFilter(LogDetail.ALL))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(body)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .body()
                .asString();

        PersonDTO result = objectMapper.readValue(content, PersonDTO.class);
        personDto = result;

        // assert not null
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getFirstName());
        assertNotNull(result.getLastName());
        assertNotNull(result.getGender());
        assertNotNull(result.getAddress());

        // assert data
        assertEquals(body.getFirstName(), result.getFirstName());
        assertEquals(body.getLastName(), result.getLastName());
        assertEquals(body.getGender(), result.getGender());
        assertEquals(body.getAddress(), result.getAddress());
    }


    @Test
    @Order(2)
    void shouldFindPersonById() throws JsonProcessingException {
        String content = given()
                .basePath("/api/person/v1/" + this.personDto.getId())
                .port(TestConfigs.SERVER_PORT)
                .filter(new RequestLoggingFilter(LogDetail.ALL))
                .filter(new ResponseLoggingFilter(LogDetail.ALL))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .asString();

        PersonDTO result = objectMapper.readValue(content, PersonDTO.class);

        // assert not null
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getFirstName());
        assertNotNull(result.getLastName());
        assertNotNull(result.getGender());
        assertNotNull(result.getAddress());

        // assert data
        assertEquals(personDto.getFirstName(), result.getFirstName());
        assertEquals(personDto.getLastName(), result.getLastName());
        assertEquals(personDto.getGender(), result.getGender());
        assertEquals(personDto.getAddress(), result.getAddress());
    }


    @Test
    @Order(3)
    void shouldFindAllPerson() throws JsonProcessingException {
        final int lengthOfPeople = 7;

        String content = given()
                .basePath("/api/person/v1")
                .port(TestConfigs.SERVER_PORT)
                .filter(new RequestLoggingFilter(LogDetail.ALL))
                .filter(new ResponseLoggingFilter(LogDetail.ALL))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .asString();

        PersonDTO[] results = objectMapper.readValue(content, PersonDTO[].class);

        // assert not null
        assertNotNull(results);
        assertEquals(lengthOfPeople, results.length);

        int index = results.length - 1;

        assertNotNull(results[index]);
        assertNotNull(results[index].getId());
        assertNotNull(results[index].getFirstName());
        assertNotNull(results[index].getLastName());
        assertNotNull(results[index].getGender());
        assertNotNull(results[index].getAddress());

        // assert data
        assertEquals(personDto.getFirstName(), results[index].getFirstName());
        assertEquals(personDto.getLastName(), results[index].getLastName());
        assertEquals(personDto.getGender(), results[index].getGender());
        assertEquals(personDto.getAddress(), results[index].getAddress());
    }

    @Test
    @Order(4)
    void shouldUpdatePersonById() {
        given()
                .basePath("/api/person/v1/" + personDto.getId())
                .port(TestConfigs.SERVER_PORT)
                .filter(new RequestLoggingFilter(LogDetail.ALL))
                .filter(new ResponseLoggingFilter(LogDetail.ALL))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(this.personDto)
                .when()
                .put()
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @Order(5)
    void shouldDeletePersonById() {
        given()
                .basePath("/api/person/v1/" + personDto.getId())
                .port(TestConfigs.SERVER_PORT)
                .filter(new RequestLoggingFilter(LogDetail.ALL))
                .filter(new ResponseLoggingFilter(LogDetail.ALL))
                .when()
                .delete()
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
