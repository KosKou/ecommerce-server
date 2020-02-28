package com.koskou.demo.service.product;

import com.koskou.demo.entity.Product;
import com.koskou.demo.servicedto.AddProductRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    //CREATE
    Mono<Product> addProduct(AddProductRequest addProductRequest);

    //RETRIEVE
    Flux<Product> retrieveAllProducts();
    Mono<Product> retrieveProductById(String id);

    //UPDATE
    Mono<Product> updateProduct();

    //DELETE
    Mono<Product> deleteProductById(String id);
}
