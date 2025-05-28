package com.nthuy.healthinsurancemanager.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
public class HealthInsuranceCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cardId;
    private String cardNumber;
    private Date issueDate;
    private Date expirationDate;
    private String status; // Còn hạn, hết hạn, chờ gia hạn,...

    @ManyToOne
    @JoinColumn(name = "user_id") // foreign key
    private Users owner;


    public HealthInsuranceCard(long cardId, String cardNumber, Date issueDate, Date expirationDate, String status, Users owner) {
        this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
        this.status = status;
        this.owner = owner;
    }

    public HealthInsuranceCard() {

    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "HealthInsuranceCard{" +
                "cardId=" + cardId +
                ", cardNumber='" + cardNumber + '\'' +
                ", issueDate=" + issueDate +
                ", expirationDate=" + expirationDate +
                ", status='" + status + '\'' +
                ", owner=" + owner +
                '}';
    }
}