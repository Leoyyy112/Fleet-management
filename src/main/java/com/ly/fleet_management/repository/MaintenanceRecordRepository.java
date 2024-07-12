package com.ly.fleet_management.repository;

import com.ly.fleet_management.entity.MaintenanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRecordRepository extends JpaRepository<MaintenanceRecord, Long> {
}
