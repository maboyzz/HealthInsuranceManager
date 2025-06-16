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
public class SystemUser {
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

    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;

    @PreUpdate
    public void handleBeforeUpdate() {
        this.updatedAt = new Date();
        this.updatedBy = "system"; // Replace with actual user context if available
    }
    @PrePersist
    public void handleBeforeCreate() {
        this.createdAt = new Date();
        this.createdBy = "system"; // Replace with actual user context if available
    }

}
