package com.nthuy.healthinsurancemanager.repository.entity;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Date;

public class BaseEntity {
    private String createdBy;
    private String updatedBy;
    private Date createdAt;
    private Date updatedAt;

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
