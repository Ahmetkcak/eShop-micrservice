package com.microservice.productservice;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");
    @LocalServerPort
    private Integer port;
    
    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static{
        mongoDBContainer.start();
    }

    @Test
    void createProductSuccessfully_ShouldReturnCreatedProductResponse(){
        String requestBody = """
                {
                    "name": "Product 1",
                    "description": "Product 1 description",
                    "price": 100.0
                }
                """;

        RestAssured.given()
        .contentType("application/json")
        .body(requestBody)
        .when()
        .post("/api/products/create")
        .then()
        .statusCode(201)
        .body("name", org.hamcrest.Matchers.equalTo("Product 1"))
        .body("description", org.hamcrest.Matchers.equalTo("Product 1 description"))
        .body("price", org.hamcrest.Matchers.equalTo(100.0f));
    }

    @Test
	void createAndGetAllProductsSuccessfully_ShouldReturnListOfGetAllProductResponse() {
		String requestBody = """
            {
                "name": "Test Product",
                "description": "Test Description",
                "price": 100.00
            }
            """;

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/products/create")
				.then()
				.statusCode(201);

		RestAssured.given()
				.when()
				.get("/api/products/getAll")
				.then()
				.statusCode(200)
				.body("size()", Matchers.greaterThan(0));
	}
}
