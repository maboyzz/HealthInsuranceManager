package com.nthuy.healthinsurancemanager.dto.response;

import com.nthuy.healthinsurancemanager.constant.CardStatus;
import com.nthuy.healthinsurancemanager.constant.EnumGender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
public class GetHealthInsuranceCardRes {
    private long cardId;
    private String cardNumber;
    private LocalDate issueDate;
    private LocalDate expirationDate;
    private String fullName;
    private String idCardNumber;
    private CardStatus status;
    private String phone;
    private String address;
    private EnumGender gender;
    private Date dateOfBirth;
    private String placeOfRegistration;
    private String areaCode;

    public GetHealthInsuranceCardRes(long cardId, String cardNumber, LocalDate issueDate, LocalDate expirationDate, String fullName, String idCardNumber, CardStatus status, String phone, String address, EnumGender gender, Date dateOfBirth, String placeOfRegistration, String areaCode) {
        this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
        this.fullName = fullName;
        this.idCardNumber = idCardNumber;
        this.status = status;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.placeOfRegistration = placeOfRegistration;
        this.areaCode = areaCode;
    }
}
