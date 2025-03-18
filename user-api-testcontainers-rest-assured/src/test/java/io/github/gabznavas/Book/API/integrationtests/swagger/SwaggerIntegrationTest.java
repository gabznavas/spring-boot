package io.github.gabznavas.Book.API.integrationtests.swagger;

import io.github.gabznavas.Book.API.config.TestConfigs;
import io.github.gabznavas.Book.API.integrationtests.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest extends AbstractIntegrationTest {

    @Test
    void shouldDisplaySwaggerUIPage() {
        String content = given()
                .basePath("/swagger-ui/index.html")
                .port(TestConfigs.SERVER_PORT)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .asString();

        assertTrue(content.contains("Swagger UI"));
    }

}
