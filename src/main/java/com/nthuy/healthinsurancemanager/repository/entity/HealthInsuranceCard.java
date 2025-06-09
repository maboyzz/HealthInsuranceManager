//package com.nthuy.healthinsurancemanager.repository.entity;
//
//import jakarta.persistence.*;
//import lombok.Setter;
//import lombok.Getter;
//
//
//import java.time.Instant;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "health_insurance_card")
//public class HealthInsuranceCard {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long cardId;
//    private String cardNumber;
//    private Instant issueDate;
//    private Instant expirationDate;
//    private String status; // Còn hạn, hết hạn, chờ gia hạn,...
//
//    @OneToOne(mappedBy = "healthInsuranceCard")
//    private UserEntity user;
//}