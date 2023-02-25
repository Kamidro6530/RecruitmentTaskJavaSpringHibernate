package com.example.dd.springapp.controller;

import com.example.dd.springapp.model.cart.Cart;
import com.example.dd.springapp.model.cart.CartRepository;
import com.example.dd.springapp.model.product.Product;
import com.example.dd.springapp.model.product.ProductRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RepositoryRestController
public class CartController {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    public CartController(final CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    Cart findCartByIdOrThrowError(int cart_id) {
        return cartRepository.findById(cart_id).orElseThrow(() -> new IllegalArgumentException("Not found Cart with id " + cart_id));
    }

    Product findProductByIdOrThrowError(int product_id) {
        return productRepository.findById(product_id).orElseThrow(() -> new IllegalArgumentException("Not found Product with id " + product_id));
    }

    @GetMapping(value = "/carts", params = {"!sort", "!page", "!size"})
    ResponseEntity<?> readAllCartsWithoutParams() {
        return ResponseEntity.ok(cartRepository.findAll());
    }

    @PostMapping(value = "/carts/{cart_id}/products/{product_id}")
    ResponseEntity<?> addProductToCart(@PathVariable("cart_id") int cart_id, @PathVariable("product_id") int product_id) {

        Cart cart = findCartByIdOrThrowError(cart_id);
        Product product = findProductByIdOrThrowError(product_id);

        int maxCountProductsInCart = 3;
        if (cart.getProducts().size() < maxCountProductsInCart) {
            product.addCart(cart);
            productRepository.save(product);
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Max count of products in cart is 3");
        }

    }


    @DeleteMapping(value = "/carts/{cart_id}/products/{product_id}")
    ResponseEntity<Cart> removeProductFromCart(@PathVariable("cart_id") int cart_id, @PathVariable("product_id") int product_id) {
        Cart cart = findCartByIdOrThrowError(cart_id);
        Product product = findProductByIdOrThrowError(product_id);
        product.removeCart(cart);
        productRepository.save(product);
        return ResponseEntity.ok(cart);
    }

    @GetMapping(value = "/carts/{cart_id}/products")
    ResponseEntity<?> getProductsFromCart(@PathVariable("cart_id") int cart_id) {
        Cart cart = cartRepository.findById(cart_id).orElseThrow(() -> new IllegalArgumentException("Not found Cart with id " + cart_id));
        return ResponseEntity.ok(cart);
    }



}
