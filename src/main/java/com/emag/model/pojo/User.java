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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    @Column(name = "full_name")
    private String name;
    @Column
    private String password;
    @Column
    private String email;
    @Column(name = "mobile_phone")
    private String mobile;
    @Column(name = "is_admin")
    private boolean isAdmin;
    @Column(name = "creted_at")
    private LocalDateTime createdAt;
    @Column
    private String gender;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "image_id")
    private UserImage image;
    @Column
    private String nickname;

}
