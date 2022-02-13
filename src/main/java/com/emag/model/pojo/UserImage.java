package com.emag.model.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "users_images")
@Setter
@Getter
@NoArgsConstructor
public class UserImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String url;
    @OneToOne(mappedBy = "image")
    @JsonBackReference
    private User user;
}
