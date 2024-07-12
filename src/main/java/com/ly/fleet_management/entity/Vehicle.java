package com.ly.fleet_management.entity;


import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public VehicleType getType() {
        return type;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public Date getLastServiceDate() {
        return lastServiceDate;
    }

    public Integer getMileage() {
        return mileage;
    }

    public String getSensorData() {
        return sensorData;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

    private Date purchaseDate;
    private Date lastServiceDate;

    public void setId(Long id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setLastServiceDate(Date lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public void setSensorData(String sensorData) {
        this.sensorData = sensorData;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    private Integer mileage;

    @Lob
    private String sensorData;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    // Getters and setters
}

enum VehicleType {
    LONG_DISTANCE,
    CITY_DELIVERY
}

enum VehicleStatus {
    ACTIVE,
    INACTIVE,
    MAINTENANCE
}
