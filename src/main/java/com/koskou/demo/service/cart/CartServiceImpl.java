package com.koskou.demo.service.cart;

import com.koskou.demo.entity.Cart;
import com.koskou.demo.entity.Product;
import com.koskou.demo.repository.CartRepository;
import com.koskou.demo.repository.ProductRepository;
import com.koskou.demo.repository.StatusRepository;
import com.koskou.demo.servicedto.AddCartRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;

    private final StatusRepository statusRepository;

    private final ProductRepository productRepository;


    @Override
    public Mono<Cart> addCart(AddCartRequest addCartRequest) {
        return addCartToRepository(addCartRequest);
    }

    private Mono<Cart> addCartToRepository(AddCartRequest addCartRequest) {
        return cartRepository.save(toCart(addCartRequest));
    }

    private Cart toCart(AddCartRequest addCartRequest) {
        List<Product> products = new ArrayList<>();
        productRepository.findById(addCartRequest.getProductId())
                .switchIfEmpty(Mono.error((Exception::new)))
                .map(products::add);
        Cart cart = Cart.builder()
                .userId(addCartRequest.getUserId())
                .products(products)
                .status("ACTIVE")
                .build();
        return cart;
    }

    @Override
    public Mono<Cart> addProductToCard(String cartId, String productId) {
        return cartRepository.findById(cartId)
                .switchIfEmpty(Mono.empty())
                .map(cart -> addProductToCardMap(cart, productId))
                .doOnSuccess(cartRepository::save);
    }

    private Cart addProductToCardMap(Cart cart, String productId) {
        Set<Product> products = new HashSet<>(cart.getProducts());
        productRepository.findById(productId)
                .switchIfEmpty(Mono.error(Exception::new))
                .doOnSuccess(products::add);
        cart.setProducts(new ArrayList<>(products));
        return cart;
    }

    @Override
    public Mono<Cart> addProductsToCard(String cartId, List<String> products) {
        return null;
    }

    @Override
    public Flux<Cart> retrieveAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Mono<Cart> retrieveCartById(String id) {
        return cartRepository.findById(id)
                .switchIfEmpty(Mono.error(Exception::new));
    }

    //TODO: Logic to updateCart
    @Override
    public Mono<Cart> updateCart() {
        return null;
    }

    @Override
    public Mono<Cart> deleteCartById(String cartId, String status) {
        return cartRepository.findById(cartId)
                .switchIfEmpty(Mono.error(Exception::new))
                .map(cart -> changeCartStatus(cart, status))
                .doOnSuccess(cartRepository::save);
    }

    private Cart changeCartStatus(Cart cart, String status){
        cart.setStatus(status);
        return cart;
    }
}
