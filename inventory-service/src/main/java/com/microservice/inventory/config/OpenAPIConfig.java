package com.microservice.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI inventorytServiceAPI() {
        return new OpenAPI()
        .info(new Info().title("Inventory Service API")
            .description("This is REST API for Inventory Service")
            .version("v1.0")
            .license(new License().name("Apache 2.0")))
        .externalDocs(new ExternalDocumentation()
            .description("You can refer to the documentation here")
            .url("https://inventory-service-dummy-url.com/docs"));
    }
}