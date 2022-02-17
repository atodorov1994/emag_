package com.emag.model.pojo;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

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
    @JoinTable(name = "category")
    @JoinColumn(name = "id")
    private Category category;
    @OneToMany(mappedBy = "subCategory")
    List<Product> products;

}
