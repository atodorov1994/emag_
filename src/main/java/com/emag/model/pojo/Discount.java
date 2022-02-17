package com.emag.model.pojo;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Component
@Entity
@Table(name = "discounts")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int discountPercent;
    private LocalDateTime startDate;
    private LocalDateTime expireDate;
}
