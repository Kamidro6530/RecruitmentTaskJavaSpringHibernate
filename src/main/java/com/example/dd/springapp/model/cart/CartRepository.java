package com.example.dd.springapp.model.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
interface CartRepository extends JpaRepository<Cart,Integer> {
}
