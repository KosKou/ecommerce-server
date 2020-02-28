package com.koskou.demo.controller;

import com.koskou.demo.entity.Product;
import com.koskou.demo.service.product.ProductService;
import com.koskou.demo.servicedto.AddProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/products")
public class ProductController {

    private final ProductService productService;

    //CREATE

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_STREAM_JSON_VALUE}
    )
    public Mono<Product> createProduct(@RequestBody AddProductRequest addProductRequest){
        return productService.addProduct(addProductRequest);
    }

    //RETRIEVE

    @GetMapping(
            produces = {MediaType.APPLICATION_STREAM_JSON_VALUE}
    )
    public Flux<Product> retrieveAllProducts(){
        return productService.retrieveAllProducts();
    }

    //UPDATE



    //DELETE
    @DeleteMapping(
            value = "/{productId}",
            produces = {MediaType.APPLICATION_STREAM_JSON_VALUE}
    )
    public Mono<Product> deleteProduct(@PathVariable(value = "productId") String productId){
        return productService.deleteProductById(productId);
    }
}
