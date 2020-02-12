package com.ibm.wiotp.external.dsc.connectors.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ConnectorsRestAPITest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/api/v0002/organizations/123456/historianconnectors")
          .then()
             .statusCode(200)
             .body(is("{\"message\": \"Hello World!!!\"}"));
    }
}