package com.nthuy.healthinsurancemanager.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateHealthInsuranceCardReq {
    @NotBlank(message = "Mã thẻ bảo hiểm không được để trống")
    private String cardNumber;

    @NotNull(message = "Ngày phát hành không được để trống")
    private Date issueDate;

    @NotNull(message = "Ngày hết hạn không được để trống")
    private Date expirationDate;

    @NotNull(message = "ID người dùng không được để trống")
    private Long userId;
}