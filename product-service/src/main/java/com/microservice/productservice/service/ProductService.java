package com.microservice.productservice.service;

import com.microservice.productservice.dto.requests.CreateProductRequest;
import com.microservice.productservice.dto.responses.CreatedProductResponse;
import com.microservice.productservice.dto.responses.GetAllProductResponse;
import com.microservice.productservice.model.Product;
import com.microservice.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public CreatedProductResponse createProduct(CreateProductRequest createProductRequest) {
        Product product = Product.builder()
                .name(createProductRequest.name())
                .description(createProductRequest.description())
                .price(createProductRequest.price())
                .build();

        productRepository.save(product);
        log.info("Product created: {}", product);
        return new CreatedProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    public List<GetAllProductResponse> getAllProduct(){
        return productRepository.findAll()
                .stream()
                .map(product -> new GetAllProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice()))
                .toList();
    }
}
