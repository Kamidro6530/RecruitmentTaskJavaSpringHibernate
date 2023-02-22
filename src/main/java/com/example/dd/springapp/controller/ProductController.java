package com.example.dd.springapp.controller;

import com.example.dd.springapp.model.product.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RepositoryRestController
public class ProductController {

    private final ProductRepository productRepository;

     ProductController(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value = "/products",params = {"!sort","!page","!size"})
    ResponseEntity<?> readAllProducts(){
        return ResponseEntity.ok(productRepository.findAll(PageRequest.ofSize(3)));
    }

    @GetMapping(value = "/products")
    ResponseEntity<?> readAllProductsPageable(Pageable page){
         return ResponseEntity.ok(productRepository.findAll(PageRequest.of(page.getPageNumber(),3)));
    }
}
