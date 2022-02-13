package com.emag.model.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Component
@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private int id;
    private String name;
    @Column(name = "sub_category_id")
    private int subCategoryId;
    @Column
    private String brand;
    @Column
    private String model;
    @Column
    private double price;
    @Column
    private String description;
    @Column
    private int quantity;
    @Column
    private int warrantyMnts;
    @Column
    private LocalDateTime addedAt;
    @Column
    private LocalDateTime deletedAt;
    @Column
    private String productscol;
    @Column
    private int discountsId;
}
