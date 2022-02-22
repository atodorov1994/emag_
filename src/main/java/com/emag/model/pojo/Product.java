package com.emag.model.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

//TODO add rating for product and calculate it every time when make review

@Component
@Entity
@Table(name = "products")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "sub_category_id" )
    @JsonManagedReference
    private SubCategory subCategory;

    private String brand;
    private String model;
    private double price;
    private Double discountedPrice;
    private String description;
    private int quantity;
    private int warrantyMonths;
    private double productRating;
    private Timestamp addedAt;
    private Timestamp deletedAt;
//    TODO not working in find all products by subcat
//    private double productRating;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "discounts_id")
    private Discount discount;

    @ManyToMany(mappedBy = "likedProducts")
    @JsonBackReference
    private List<User> usersLikedThisProduct;

    @OneToMany(mappedBy = "product")
    @JsonBackReference
    private List<Review> reviews;

    @OneToMany(mappedBy = "product")
    @JsonBackReference
    private List<UserCart> productsInCart;

    @OneToMany(mappedBy = "product")
    @JsonBackReference
    private List<OrderedProduct> orderedProducts;







}
