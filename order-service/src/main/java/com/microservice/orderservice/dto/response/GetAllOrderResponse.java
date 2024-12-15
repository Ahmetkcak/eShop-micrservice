package com.microservice.orderservice.dto.response;

public record GetAllOrderResponse(Long id, String orderNumber,String skuCode, Double price, Integer quantity) {}
