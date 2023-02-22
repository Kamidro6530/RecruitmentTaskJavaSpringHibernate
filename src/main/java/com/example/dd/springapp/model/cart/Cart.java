package com.example.dd.springapp.model.cart;

import com.example.dd.springapp.model.product.Product;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int id;
    @Column(name = "cart_title")
    private String title;
    @JsonManagedReference
    @ManyToMany(mappedBy = "carts", cascade = { CascadeType.ALL })
    private Set<Product> products = new HashSet<Product>();


     Cart(){

     }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
