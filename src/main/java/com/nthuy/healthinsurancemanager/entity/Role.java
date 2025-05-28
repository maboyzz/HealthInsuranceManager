package com.nthuy.healthinsurancemanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Role {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long roleId;
        private String roleName; // Admin, Nhân viên, Người dùng,...
    }

