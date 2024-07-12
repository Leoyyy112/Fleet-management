// src/main/java/com/ly/fleet_management/controller/ReportController.java
package com.ly.fleet_management.controller;

import com.ly.fleet_management.dto.ReportDTO;
import com.ly.fleet_management.entity.Report;
import com.ly.fleet_management.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/generate")
    public Report generateReport(@RequestBody ReportDTO reportDTO) {
        return reportService.generateReport(reportDTO.getName());
    }

    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }
}
