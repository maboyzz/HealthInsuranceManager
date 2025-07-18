package com.nthuy.healthinsurancemanager.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class RenewHealthCardReq {
    @NotBlank(message = "Mã thẻ bảo hiểm không được để trống")
    private String cardNumber;

    @NotNull(message = "Ngày phát hành không được để trống")
    private LocalDate issueDate;

    @NotNull(message = "Ngày hết hạn không được để trống")
    private LocalDate expirationDate;
}
