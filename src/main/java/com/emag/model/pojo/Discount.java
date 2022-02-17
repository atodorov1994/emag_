package com.emag.model.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Entity
@Table(name = "discounts")
@Data
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int discountPercent;
    private Timestamp startDate;
    private Timestamp expireDate;
    @OneToMany(mappedBy = "discount")
    List<Product> products;

}
