package com.emag.model.pojo;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String name;
    @Column
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
