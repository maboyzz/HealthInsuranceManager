package com.nthuy.healthinsurancemanager.dto.request;

import com.nthuy.healthinsurancemanager.constant.EnumGender;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateSystemUserRequest {
    @NotBlank(message = "tên không đươc để trống")
    private String fullName;
    private Date dateOfBirth;
    private EnumGender gender;
    @NotBlank(message = "CCCD không đươc để trống")
    private String idCardNumber;
    private String phone;
    private String address;
}
