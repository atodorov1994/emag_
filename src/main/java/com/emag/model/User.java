package com.emag.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue
    private long id;
    @Column
    @NotNull
    private String name;
    @Column
    @NotNull
    private String password;
    @Column
    @NotNull
    private String email;
    @Column
    @NotNull
    private String mobile;
    @Column
    @NotNull
    private boolean isAdmin;
    @Column
    private LocalDateTime createdAt;
    @Column
    private String gender;
    @Column
    private LocalDate birthDate;
//    @OneToMany(mappedBy = "users_images")
//    private Set<Integer> imgId;
    @Column
    private String nickname;

}
