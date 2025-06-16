package com.nthuy.healthinsurancemanager.dto.response;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class GetEntityUsersRes {
    private Long id;
    private String userName;
    private String fullName;
    private Date dateOfBirth;
    private String phone;
    private String email;

    public GetEntityUsersRes(Long id, String userName, String fullName, Date dateOfBirth, String phone, String email) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
    }
}
