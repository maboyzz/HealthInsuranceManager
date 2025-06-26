package com.nthuy.healthinsurancemanager.dto.response;

import com.nthuy.healthinsurancemanager.constant.CardStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class GetHealthInsuranceCardRes {
    private long cardId;
    private String cardNumber;
    private Date issueDate;
    private Date expirationDate;
    private String fullName;
    private String idCardNumber;
    private CardStatus status;

    public GetHealthInsuranceCardRes(long cardId, String cardNumber, Date issueDate, Date expirationDate, String fullName, String idCardNumber, CardStatus status) {
       this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
        this.fullName = fullName;
        this.idCardNumber = idCardNumber;
        this.status = status;
    }
}
