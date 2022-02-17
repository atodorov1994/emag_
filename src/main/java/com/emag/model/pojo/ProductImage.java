package com.emag.model.pojo;


import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "products_images")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String url;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
