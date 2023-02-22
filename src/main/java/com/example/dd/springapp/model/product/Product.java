package com.example.dd.springapp.model.product;

import com.example.dd.springapp.model.cart.Cart;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;
    @NotBlank(message = "Product title must not be empty")
    @Column(name = "product_title")
    private String title;
    @Column(name = "product_price")
    private double price;
    @JsonBackReference
    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(
            name = "carts_products",
            joinColumns = {
                    @JoinColumn(name = "product_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "cart_id")
            }
    )
    Set< Cart > carts = new HashSet< Cart >();


    Product() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }

    public void addCart(Cart cart) {
        this.carts.add(cart);
        cart.getProducts().add(this);
    }

    public void removeCart(Cart cart) {
        this.carts.remove(cart);
        cart.getProducts().remove(this);
    }
}
