package com.nthuy.healthinsurancemanager.dto.request;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class Role {
    private long roleId;
    private String name;
    private String description; // Admin, Nhân viên, Người dùng,...

}

