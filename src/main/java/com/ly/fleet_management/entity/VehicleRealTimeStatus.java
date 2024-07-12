package com.ly.fleet_management.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vehicle_real_time_status")
public class VehicleRealTimeStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long vehicleId;
    private String status;
    private Date timestamp;

    // Getters and Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public String getStatus() {
        return status;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
