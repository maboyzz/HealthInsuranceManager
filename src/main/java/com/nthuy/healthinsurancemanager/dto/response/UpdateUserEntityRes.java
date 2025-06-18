package com.nthuy.healthinsurancemanager.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class UpdateUserEntityRes {
    private String fullName;
    private Date dateOfBirth;
    private String gender;
    private String job;
    private String idCardNumber;
    private String phone;
    private String address;
}
