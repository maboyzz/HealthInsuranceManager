package com.nthuy.healthinsurancemanager.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class RenewalRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long requestId;

    private Date requestDate;
    private String status; // VD: Đã xử lý, Đang chờ, Bị từ chối

    @ManyToOne
    @JoinColumn(name = "card_id") // FK đến thẻ bảo hiểm
    private HealthInsuranceCard card;

    @ManyToOne
    @JoinColumn(name = "processed_by") // FK đến nhân viên xử lý (User)
    private Users processedBy;

    // Getters and Setters

    public long getRequestId() { return requestId; }
    public void setRequestId(long requestId) { this.requestId = requestId; }

    public Date getRequestDate() { return requestDate; }
    public void setRequestDate(Date requestDate) { this.requestDate = requestDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public HealthInsuranceCard getCard() { return card; }
    public void setCard(HealthInsuranceCard card) { this.card = card; }

    public Users getProcessedBy() { return processedBy; }
    public void setProcessedBy(Users processedBy) { this.processedBy = processedBy; }
}
