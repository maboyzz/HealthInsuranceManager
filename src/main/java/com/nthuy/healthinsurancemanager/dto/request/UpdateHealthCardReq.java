package com.nthuy.healthinsurancemanager.dto.request;

import com.nthuy.healthinsurancemanager.constant.CardStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
public class UpdateHealthCardReq {
    @NotBlank(message = "Mã thẻ bảo hiểm không được để trống")
    private String cardNumber;

    @NotNull(message = "Ngày phát hành không được để trống")
    private LocalDate issueDate;

    @NotNull(message = "Ngày hết hạn không được để trống")
    private LocalDate expirationDate;

    @NotBlank(message = "Số CCCD không được để trống")
    @Size(min = 12, max = 12, message = "Số CMND/CCCD phải có đúng 12 ký tự")
    private String idCardNumber;

    @NotBlank(message = "Nơi đăng ký khám chữa bệnh ban đầu không được để trống")
    private String placeOfRegistration;
    @NotBlank(message = "Mã khu vực không được để trống")
    private String areaCode;

    private CardStatus status;
}
