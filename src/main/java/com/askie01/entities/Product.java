package com.askie01.entities;

import com.askie01.products.ProductCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "products")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String name;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    private File image;
    private String description;
    private int amount;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @ManyToMany
    @JoinTable(
            name = "products_consumers",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "consumer_id")
    )
    private List<User> consumers;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", image=" + image +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", seller=" + seller.getUsername() +
                '}';
    }
}
