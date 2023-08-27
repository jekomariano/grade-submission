package com.ltp.gradesubmission.entity;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Table(name = "users")
@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "username should not be blank.")
    @NonNull
    @Column(nullable = false)
    private String username;

    @NotBlank(message = "Password cannot be empty.")
    @NonNull
    @Column(nullable = false)
    private String password;

}
