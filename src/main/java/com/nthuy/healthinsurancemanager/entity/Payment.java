package com.nthuy.healthinsurancemanager.entity;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentId;

    private Date paymentDate;
    private double amount;
    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "card_id") // FK đến thẻ bảo hiểm
    private HealthInsuranceCard card;

    @ManyToOne
    @JoinColumn(name = "user_id") // FK đến người thanh toán
    private Users user;

    // Getters and Setters

    public long getPaymentId() { return paymentId; }
    public void setPaymentId(long paymentId) { this.paymentId = paymentId; }

    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public HealthInsuranceCard getCard() { return card; }
    public void setCard(HealthInsuranceCard card) { this.card = card; }

    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }
}
