package com.nthuy.healthinsurancemanager.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateUserEntityReq {

    @NotBlank(message = "Họ và tên không được để trống")
    @Size(min = 6, max = 30, message = "Họ và tên phải có độ dài từ 6 đến 30 ký tự")
    private String fullName;
    private Date dateOfBirth;
    private String gender;
    private String job;
    @NotBlank(message = "Số CCCD không được để trống")
    @Size(min = 12, max = 12, message = "Số CMND/CCCD phải có đúng 12 ký tự")
    private String idCardNumber;
    private String phone;
    private String address;
}
