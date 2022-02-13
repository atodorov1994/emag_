package com.emag.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullName;
    private String password;
    private String email;
    private String mobilePhone;
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


}
