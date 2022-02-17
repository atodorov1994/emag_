package com.emag.model.pojo;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Component
@Entity
@Table(name = "products")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;
    private String brand;
    private String model;
    private double price;
    private String description;
    private int quantity;
    private int warrantyMonths;
    private LocalDateTime addedAt;
    private LocalDateTime deletedAt;
    private double productRating;
    private int discountsId;
}
