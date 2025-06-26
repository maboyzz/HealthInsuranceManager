package com.nthuy.healthinsurancemanager.dto.request;

import com.nthuy.healthinsurancemanager.constant.EnumGender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
@Getter
@Setter
public class CreateSystemUserRequest {
    private long id;
    @NotBlank(message = "User name Không được để trống")
    @Size(min = 3, max = 20, message = "User name phải có độ dài từ 3 đến 20 ký tự")
    private String userName;
    @Size(min = 6, max = 30, message = "Full name phải có độ dài từ 6 đến 30 ký tự")
    @NotBlank(message = "Full name Không được để trống")
    private String fullName;
    private Date dateOfBirth;
    private EnumGender gender;
    @NotBlank(message = "Số CCCD không được để trống")
    @Size(min = 12, max = 12, message = "Số CMND/CCCD phải có đúng 12 ký tự")
    private String idCardNumber;
    @NotBlank(message = "Password Không được để trống")
    private String passWord;
    private String phone;
    private String address;
    @Email(message = "Email không hợp lệ")
    private String email;
    private int roleID;

}
