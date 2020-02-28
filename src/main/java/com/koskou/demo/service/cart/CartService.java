package com.koskou.demo.service.cart;

import com.koskou.demo.entity.Cart;
import com.koskou.demo.servicedto.AddCartRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CartService {

    //CREATE
    Mono<Cart> addCart(AddCartRequest addCartRequest);
    Mono<Cart> addProductToCard(String cartId, String productId);
    Mono<Cart> addProductsToCard(String cartId, List<String> products);

    //RETRIEVE
    Flux<Cart> retrieveAllCarts();
    Mono<Cart> retrieveCartById(String id);

    //UPDATE
    Mono<Cart> updateCart();

    //DELETE
    Mono<Cart> deleteCartById(String id, String status);
}
