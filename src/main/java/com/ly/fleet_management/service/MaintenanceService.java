package com.ly.fleet_management.service;

import com.ly.fleet_management.entity.MaintenanceRecord;
import com.ly.fleet_management.entity.Vehicle;
import com.ly.fleet_management.repository.MaintenanceRecordRepository;
import com.ly.fleet_management.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRecordRepository maintenanceRecordRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<MaintenanceRecord> getAllRecords() {
        return maintenanceRecordRepository.findAll();
    }

    public Optional<MaintenanceRecord> getRecordById(Long id) {
        return maintenanceRecordRepository.findById(id);
    }

    public MaintenanceRecord saveRecord(MaintenanceRecord record) {
        if (record.getVehicle() == null || !vehicleRepository.existsById(record.getVehicle().getId())) {
            throw new IllegalArgumentException("Vehicle must not be null and must exist");
        }
        return maintenanceRecordRepository.save(record);
    }

    public void deleteRecord(Long id) {
        maintenanceRecordRepository.deleteById(id);
    }
}
