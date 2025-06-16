package com.nthuy.healthinsurancemanager.dto.response;

import com.nthuy.healthinsurancemanager.constant.EnumGender;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateSystemUserResponse {

    private String fullName;
    private Date dateOfBirth;
    private EnumGender gender;
    private String idCardNumber;
    private String phone;
    private String address;
}
