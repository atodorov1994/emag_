package com.emag.model.pojo;


import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "sub_categories")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String subcategoryName;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
