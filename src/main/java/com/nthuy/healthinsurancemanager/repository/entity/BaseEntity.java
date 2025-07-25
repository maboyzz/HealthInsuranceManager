package com.nthuy.healthinsurancemanager.repository.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Date;

@MappedSuperclass
public class BaseEntity {
    protected String createdBy;
    protected String updatedBy;
    protected Date createdAt;
    protected Date updatedAt;

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
