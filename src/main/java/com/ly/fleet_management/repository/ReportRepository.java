// src/main/java/com/ly/fleet_management/repository/ReportRepository.java
package com.ly.fleet_management.repository;

import com.ly.fleet_management.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}
