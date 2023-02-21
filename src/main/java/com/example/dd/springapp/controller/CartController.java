package com.example.dd.springapp.controller;

import com.example.dd.springapp.model.cart.CartRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public class CartController {

    private final CartRepository cartRepository;

    public CartController(final CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
}
