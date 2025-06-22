package com.nthuy.healthinsurancemanager.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class GetHealthInsuranceCardRes {
    private String cardNumber;
    private Date issueDate;
    private Date expirationDate;
    private Long userId;
}
