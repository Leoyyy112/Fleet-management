package com.ly.fleet_management.repository;

import com.ly.fleet_management.entity.VehicleRealTimeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRealTimeStatusRepository extends JpaRepository<VehicleRealTimeStatus, Long> {
}
