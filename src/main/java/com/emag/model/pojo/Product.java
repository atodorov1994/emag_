package com.emag.model.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne
    @JoinTable(name = "sub_categories")
    @JoinColumn(name = "id")
    private SubCategory subCategory;

    private String brand;
    private String model;
    private double price;
    private String description;
    private int quantity;
    private int warrantyMonths;
    private Timestamp addedAt;
    private Timestamp deletedAt;
    private double productRating;

    @ManyToOne
    @JoinTable(name = "discounts")
    @JoinColumn(name = "id")
    private Discount discount;

    @ManyToMany(mappedBy = "likedProducts")
    @JsonBackReference
    private List<User> usersLikedThisProduct;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;




}
