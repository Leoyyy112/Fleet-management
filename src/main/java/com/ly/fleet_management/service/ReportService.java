// src/main/java/com/ly/fleet_management/service/ReportService.java
package com.ly.fleet_management.service;

import com.ly.fleet_management.entity.Report;
import com.ly.fleet_management.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public Report generateReport(String name) {
        // 这里添加报表生成逻辑，假设生成的报表数据是 "Sample Data"
        Report report = new Report();
        report.setName(name);
        report.setData("Sample Data");
        return reportRepository.save(report);
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }
}
