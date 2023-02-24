package com.example.dd.springapp.model.cart;

import com.example.dd.springapp.model.product.Product;
import jakarta.persistence.*;

import java.util.HashSet;
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

    public double getProductsPrice(){
         double pricesSum = 0;
        for (Product x : products){
            pricesSum += x.getPrice();
        }
        return pricesSum;
    }
}
