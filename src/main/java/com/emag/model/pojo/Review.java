package com.emag.model.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Component
@Entity
@Table(name = "reviews")
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinTable(name = "users")
    @JoinColumn(name = "id")
    private User reviewer;

    @ManyToOne
    @JoinTable(name = "products")
    @JoinColumn(name = "id")
    private Product product;

    private String title;
    private String description;
    private int rating;
    private LocalDateTime createdAt;

}
