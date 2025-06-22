package com.nthuy.healthinsurancemanager.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;
    private String fullName;
    private Date dateOfBirth;
    private String gender;
    private String job;
    private String idCardNumber;
    private String passWord;
    private String phone;
    private String address;
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

}
