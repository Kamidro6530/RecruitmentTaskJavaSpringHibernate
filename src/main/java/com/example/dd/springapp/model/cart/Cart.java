package com.example.dd.springapp.model.cart;

import com.example.dd.springapp.model.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int id;
    @Column(name = "cart_title")
    private String title;
    @ManyToMany(mappedBy = "carts", cascade = { CascadeType.ALL })
    private Set<Product> products = new HashSet<Product>();

    public double getProductsPrice(){
         double pricesSum = 0;
        for (Product x : products){
            pricesSum += x.getPrice();
        }
        return pricesSum;
    }
}
