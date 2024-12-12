package com.microservice.productservice.dto.responses;

public record CreatedProductResponse(String id, String name, String description, Double price) { }
