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
    @Column(name = "user_id")
    private long id;
    @Column(name = "full_name")
    private String name;
    private String password;
    private String email;
    @Column(name = "mobile_phone")
    private String mobile;
    @Column(name = "is_admin")
    private boolean isAdmin;
    @Column(name = "creted_at")
    private Timestamp createdAt;
    private String gender;
    @Column(name = "birth_date")
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
