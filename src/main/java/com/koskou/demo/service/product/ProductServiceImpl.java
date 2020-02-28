package com.koskou.demo.service.product;

import com.koskou.demo.entity.Product;
import com.koskou.demo.repository.ProductRepository;
import com.koskou.demo.repository.StatusRepository;
import com.koskou.demo.servicedto.AddProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public Mono<Product> addProduct(AddProductRequest addProductRequest) {
        return addProductToRepository(addProductRequest);
    }

    private Mono<Product> addProductToRepository(AddProductRequest addProductRequest) {
        return productRepository.findByDescription(addProductRequest.getDescription())
                .switchIfEmpty(productRepository.save(toProduct(addProductRequest)));
    }

    private Product toProduct(AddProductRequest addProductRequest) {
        Product product = Product.builder()
                .description(addProductRequest.getDescription())
                .price(addProductRequest.getPrice())
                .stock(addProductRequest.getStock())
                .status("ACTIVE")
                .build();
        return product;
    }


    @Override
    public Flux<Product> retrieveAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Mono<Product> retrieveProductById(String id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.empty());
    }

    //TODO: Logic to update products
    @Override
    public Mono<Product> updateProduct() {
        return null;
    }

    @Override
    public Mono<Product> deleteProductById(String id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.empty())
                .map(product -> changeProductStatus(product, "INACTIVE"))
                .doOnSuccess(productRepository::save);
    }

    private Product changeProductStatus(Product product, String status) {
        product.setStatus(status);
        return product;
    }
}
