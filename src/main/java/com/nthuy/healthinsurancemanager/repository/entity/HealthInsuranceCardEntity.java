package com.nthuy.healthinsurancemanager.repository.entity;

import com.nthuy.healthinsurancemanager.constant.CardStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "health_insurance_card")
public class HealthInsuranceCardEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cardId;

    private String cardNumber;

    private LocalDate issueDate;
    private LocalDate  expirationDate;
    private String placeOfRegistration;
    private String areaCode;
    private CardStatus status;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
}