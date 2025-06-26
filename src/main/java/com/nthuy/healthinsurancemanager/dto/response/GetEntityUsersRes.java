package com.nthuy.healthinsurancemanager.dto.response;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class GetEntityUsersRes {
    private String fullName;
    private String idCardNumber;
    private Date dateOfBirth;
    private String phone;
    private String email;
    private String roleName;

    public GetEntityUsersRes(String fullName,String idCardNumber, Date dateOfBirth, String phone, String email, String roleName) {
        this.fullName = fullName;
        this.idCardNumber = idCardNumber;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.roleName = roleName;
    }
}
