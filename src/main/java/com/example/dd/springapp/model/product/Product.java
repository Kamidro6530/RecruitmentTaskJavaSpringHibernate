package com.example.dd.springapp.model.product;

import com.example.dd.springapp.model.cart.Cart;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    @JsonIgnore
    Set< Cart > carts = new HashSet< Cart >();


    public void addCart(Cart cart) {
        this.carts.add(cart);
        cart.getProducts().add(this);
    }

    public void removeCart(Cart cart) {
        this.carts.remove(cart);
        cart.getProducts().remove(this);
    }
}
