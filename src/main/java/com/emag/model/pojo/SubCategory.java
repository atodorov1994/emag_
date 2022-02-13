package com.emag.model.pojo;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "sub_categories")
@Data
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String subcategoryName;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
