package com.nthuy.healthinsurancemanager.dto.request;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
@Getter
@Setter
public class CreateSystemUserRequest {
    private long id;

    private String userName;
    private String fullName;
    private Date dateOfBirth;
    private String gender;
    private String idCardNumber;
    private String passWord;
    private String phone;
    private String address;
    private String email;
    private int roleID;

}
