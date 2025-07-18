package com.nthuy.healthinsurancemanager.dto.response;

import com.nthuy.healthinsurancemanager.constant.CardStatus;
import com.nthuy.healthinsurancemanager.constant.EnumGender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class GetCardByIdRes {
    private String cardNumber;
    private LocalDate issueDate;
    private LocalDate expirationDate;
    private String idCardNumber;
    private CardStatus status;
    private String placeOfRegistration;
    private String areaCode;

    public GetCardByIdRes(String cardNumber, LocalDate issueDate, LocalDate expirationDate, String idCardNumber, CardStatus status, String placeOfRegistration, String areaCode) {
        this.cardNumber = cardNumber;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
        this.idCardNumber = idCardNumber;
        this.status = status;
        this.placeOfRegistration = placeOfRegistration;
        this.areaCode = areaCode;
    }
}

