package com.example.easynotes.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long equipmentID;

    @Column(nullable = false, length = 100)
    private String equipmentName;

    @Column(columnDefinition = "TEXT")
    private String equipmentDescription;

    private String equipmentType;
    private String purchaseDate;
    private BigDecimal purchasePrice;
    private String currentStatus;

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentDescription() {return equipmentDescription;}

    public void setEquipmentDescription(String equipmentDescription) { this.equipmentDescription = equipmentDescription; }

    public String getEquipmentType() { return equipmentType; }

    public void setEquipmentType(String equipmentType) { this.equipmentType = equipmentType; }

    public String getPurchaseDate() { return purchaseDate; }

    public void setPurchaseDate(String purchaseDate) { this.purchaseDate = purchaseDate; }

    public BigDecimal getPurchasePrice() { return purchasePrice; }

    public void setPurchasePrice(BigDecimal purchasePrice) { this.purchasePrice = purchasePrice; }

    public String getCurrentStatus() { return currentStatus; }

    public void setCurrentStatus(String currentStatus) { this.currentStatus = currentStatus; }

}
