package com.ly.fleet_management.controller;

import com.ly.fleet_management.entity.Vehicle;
import com.ly.fleet_management.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .map(vehicle -> ResponseEntity.ok(vehicle))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.saveVehicle(vehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicleDetails) {
        return vehicleService.getVehicleById(id)
                .map(vehicle -> {
                    vehicle.setModel(vehicleDetails.getModel());
                    vehicle.setStatus(vehicleDetails.getStatus());
                    vehicle.setMileage(vehicleDetails.getMileage());
                    Vehicle updatedVehicle = vehicleService.saveVehicle(vehicle);
                    return ResponseEntity.ok(updatedVehicle);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .map(vehicle -> {
                    vehicleService.deleteVehicle(id);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
