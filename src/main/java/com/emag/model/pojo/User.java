package com.emag.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.awt.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Component
@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullName;
    private String password;
    private String email;
    private String mobilePhone;
    @Column(name = "is_admin" , nullable = false , columnDefinition = "TINYINT(1)")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isAdmin;
    private Timestamp createdAt;
    private String gender;
    private LocalDate birthDate;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "image_id")
    private UserImage image;

    private String nickname;

//    Adressess many to many relationship
    @ManyToMany
    @JsonManagedReference
    @JoinTable(
            name="users_addresses",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="address_id")}
    )
    private List<Address> addresses;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(
            name = "users_like_products" ,
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn (name = "product_id")}
    )
    private List<Product> likedProducts;

    @OneToMany(mappedBy = "reviewer")
    private List<Review> reviews;

    




}
