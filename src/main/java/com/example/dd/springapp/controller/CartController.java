package com.example.dd.springapp.controller;

import com.example.dd.springapp.model.cart.Cart;
import com.example.dd.springapp.model.cart.CartRepository;
import com.example.dd.springapp.model.product.Product;
import com.example.dd.springapp.model.product.ProductRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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

    @GetMapping(value = "/carts",params = {"!sort","!page","!size"})
    ResponseEntity<?> readAllCarts(){
        return ResponseEntity.ok(cartRepository.findAll());
    }

    @PostMapping(value = "/carts/{cart_id}/products/{product_id}")
    ResponseEntity<Cart> addProductToCart(@PathVariable("cart_id") int cart_id,@PathVariable("product_id") int product_id){
        Cart cart = cartRepository.findById(cart_id).orElseThrow(()-> new ResourceNotFoundException("Not found Cart with id "+cart_id));
        Product product = productRepository.findById(product_id).orElseThrow(()-> new ResourceNotFoundException("Not found Product with id "+product_id));
        product.addCart(cart);
        productRepository.save(product);
        return ResponseEntity.ok(cart);
    }


    @DeleteMapping(value = "/carts/{cart_id}/products/{product_id}")
    ResponseEntity<Cart> removeProductFromCart(@PathVariable("cart_id") int cart_id,@PathVariable("product_id") int product_id){
        Cart cart = cartRepository.findById(cart_id).orElseThrow(()-> new ResourceNotFoundException("Not found Cart with id "+cart_id));
        Product product = productRepository.findById(product_id).orElseThrow(()-> new ResourceNotFoundException("Not found Product with id "+product_id));
        product.removeCart(cart);
        productRepository.save(product);
        return ResponseEntity.ok(cart);
    }

}
