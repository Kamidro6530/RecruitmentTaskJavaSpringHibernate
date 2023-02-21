package com.example.dd.springapp.controller;

import com.example.dd.springapp.model.product.ProductRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public class ProductController {

    private final ProductRepository productRepository;

     ProductController(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
