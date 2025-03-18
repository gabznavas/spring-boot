package io.github.gabznavas.Book.API.integrationtests.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.gabznavas.Book.API.config.TestConfigs;
import io.github.gabznavas.Book.API.data.dto.v1.BookDTO;
import io.github.gabznavas.Book.API.integrationtests.testcontainers.AbstractIntegrationTest;
import io.github.gabznavas.Book.API.mapper.mocks.MockBook;
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
public class BookIntegrationTest extends AbstractIntegrationTest {

    private static MockBook input;

    private static ObjectMapper objectMapper;

    private static BookDTO bookDto;

    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.registerModule(new JavaTimeModule());

        input = new MockBook();
    }

    @Test
    @Order(1)
    void shouldCreateABook() throws JsonProcessingException {
        BookDTO body = input.mockDTO();

        String content = given()
                .basePath("/api/v1/book")
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

        BookDTO result = objectMapper.readValue(content, BookDTO.class);
        bookDto = result;

        // assert not null
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getTitle());
        assertNotNull(result.getLaunch());
        assertNotNull(result.getAuthor());

        // assert data
        assertEquals(body.getTitle(), result.getTitle());
        assertEquals(body.getLaunch(), result.getLaunch());
        assertEquals(body.getAuthor(), result.getAuthor());
    }

    @Test
    @Order(2)
    void shouldFindBookById() throws JsonProcessingException {
        String content = given()
                .basePath("/api/v1/book/" + bookDto.getId())
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

        BookDTO result = objectMapper.readValue(content, BookDTO.class);

        // assert not null
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getTitle());
        assertNotNull(result.getLaunch());
        assertNotNull(result.getAuthor());

        // assert data
        assertEquals(bookDto.getTitle(), result.getTitle());
        assertEquals(bookDto.getLaunch(), result.getLaunch());
        assertEquals(bookDto.getAuthor(), result.getAuthor());
    }

    @Test
    @Order(3)
    void shouldFindAllBooks() throws JsonProcessingException {
        String content = given()
                .basePath("/api/v1/book")
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

        BookDTO[] results = objectMapper.readValue(content, BookDTO[].class);

        // assert not null
        assertNotNull(results);

        int lengthOfBook = 16;
        int index = results.length - 1;
        assertEquals(lengthOfBook, results.length);

        assertNotNull(results[0].getId());
        assertNotNull(results[0].getTitle());
        assertNotNull(results[0].getLaunch());
        assertNotNull(results[0].getAuthor());

        // assert data
        assertEquals(bookDto.getTitle(), results[index].getTitle());
        assertEquals(bookDto.getLaunch(), results[index].getLaunch());
        assertEquals(bookDto.getAuthor(), results[index].getAuthor());
    }

    @Test
    @Order(4)
    void shouldUpdateBookById() throws JsonProcessingException {
        given()
                .basePath("/api/v1/book/" + bookDto.getId())
                .port(TestConfigs.SERVER_PORT)
                .filter(new RequestLoggingFilter(LogDetail.ALL))
                .filter(new ResponseLoggingFilter(LogDetail.ALL))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(bookDto)
                .when()
                .put()
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }


    @Test
    @Order(5)
    void shouldDeleteBookById() throws JsonProcessingException {
        given()
                .basePath("/api/v1/book/" + bookDto.getId())
                .port(TestConfigs.SERVER_PORT)
                .filter(new RequestLoggingFilter(LogDetail.ALL))
                .filter(new ResponseLoggingFilter(LogDetail.ALL))
                .body(bookDto)
                .when()
                .delete()
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
