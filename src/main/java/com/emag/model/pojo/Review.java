package com.emag.model.pojo;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Component
@Entity
@Table(name = "reviews")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User reviewer;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private String title;
    private String description;
    private int rating;
    private LocalDateTime createdAt;

}
