package com.microservice.productservice.api.controllers;

import com.microservice.productservice.dto.requests.CreateProductRequest;
import com.microservice.productservice.dto.responses.CreatedProductResponse;
import com.microservice.productservice.dto.responses.GetAllProductResponse;
import com.microservice.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductService productService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProductResponse createProduct(@RequestBody CreateProductRequest createProductRequest) {
        return productService.createProduct(createProductRequest);
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllProductResponse> getAllProduct(){
        return productService.getAllProduct();
    }
}
