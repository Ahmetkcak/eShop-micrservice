package com.microservice.inventory;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class InventoryServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:latest");
	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mySQLContainer.start();
	}

	@Test
	public void isInStock_ShouldReturnFalse_WhenItemIsNotInStock() {
		String skuCode = "iphone_15";
		int quantity = 1;

		var responseBodyString = RestAssured.given()
				.contentType("application/json")
				.queryParam("skuCode", skuCode)
				.queryParam("quantity", quantity)
				.when()
				.get("/api/inventory")
				.then()
				.log().all()
				.statusCode(200)
				.extract()
				.body().asString();

		assertThat(responseBodyString, Matchers.is("false"));
	}
}
