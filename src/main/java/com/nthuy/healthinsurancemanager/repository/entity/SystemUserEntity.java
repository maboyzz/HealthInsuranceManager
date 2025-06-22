package com.nthuy.healthinsurancemanager.repository.entity;

import com.nthuy.healthinsurancemanager.constant.EnumGender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "system_user")
public class SystemUserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;
    private String fullName;
    private Date dateOfBirth;
    private EnumGender gender;
    private String idCardNumber;
    private String passWord;
    private String phone;
    private String address;
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;


}
