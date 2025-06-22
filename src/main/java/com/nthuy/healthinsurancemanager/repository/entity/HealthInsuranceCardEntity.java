package com.nthuy.healthinsurancemanager.repository.entity;

import com.nthuy.healthinsurancemanager.constant.CardStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "health_insurance_card")
public class HealthInsuranceCardEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cardId;

    @NotBlank(message = "Mã thẻ bảo hiểm không được để trống")
    @Column(unique = true)
    private String cardNumber;

    private Date issueDate;
    private Date expirationDate;

    @Enumerated(EnumType.STRING)
    private CardStatus status;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;


    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;


}