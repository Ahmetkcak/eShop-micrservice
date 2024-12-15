package com.microservice.orderservice.dto.request;

public record PlaceOrderRequest(Long id, String orderNumber,String skuCode, Double price, Integer quantity) {}