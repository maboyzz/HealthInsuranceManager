package com.nthuy.healthinsurancemanager.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class GetSystemUserResponse {
    private Long id;
    private String userName;
    private String fullName;
    private Date dateOfBirth;
    private String phone;
    private String email;

    public GetSystemUserResponse(Long id, String userName, Date dateOfBirth, String fullName, String phone, String email) {
        this.id = id;
        this.userName = userName;
        this.dateOfBirth = dateOfBirth;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
    }


    @Override
    public String toString() {
        return "GetSystemUserResponse{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
