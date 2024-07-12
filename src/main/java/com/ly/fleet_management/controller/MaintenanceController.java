package com.ly.fleet_management.controller;

import com.ly.fleet_management.entity.MaintenanceRecord;
import com.ly.fleet_management.entity.Vehicle;
import com.ly.fleet_management.service.MaintenanceService;
import com.ly.fleet_management.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance-records")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public List<MaintenanceRecord> getAllRecords() {
        return maintenanceService.getAllRecords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceRecord> getRecordById(@PathVariable Long id) {
        return maintenanceService.getRecordById(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MaintenanceRecord createRecord(@RequestBody MaintenanceRecord record) {
        Vehicle vehicle = record.getVehicle();
        if (vehicle == null || !vehicleService.getVehicleById(vehicle.getId()).isPresent()) {
            throw new IllegalArgumentException("Vehicle must not be null and must exist");
        }
        return maintenanceService.saveRecord(record);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceRecord> updateRecord(@PathVariable Long id, @RequestBody MaintenanceRecord recordDetails) {
        Vehicle vehicle = recordDetails.getVehicle();
        if (vehicle == null || !vehicleService.getVehicleById(vehicle.getId()).isPresent()) {
            throw new IllegalArgumentException("Vehicle must not be null and must exist");
        }
        return maintenanceService.getRecordById(id)
                .map(record -> {
                    record.setMaintenanceDate(recordDetails.getMaintenanceDate());
                    record.setDescription(recordDetails.getDescription());
                    record.setStatus(recordDetails.getStatus());
                    record.setVehicle(vehicle);
                    MaintenanceRecord updatedRecord = maintenanceService.saveRecord(record);
                    return ResponseEntity.ok(updatedRecord);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        return maintenanceService.getRecordById(id)
                .map(record -> {
                    maintenanceService.deleteRecord(id);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
