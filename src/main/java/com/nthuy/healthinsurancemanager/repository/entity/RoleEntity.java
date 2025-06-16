package com.nthuy.healthinsurancemanager.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleId;
    private String name;
    private String description; // Admin, Nhân viên, Người dùng,...

    @OneToMany(mappedBy = "role")
    private List<SystemUser> users;
    @OneToMany(mappedBy = "role")
    private List<UserEntity> userEntities;
}

