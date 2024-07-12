package com.ly.fleet_management.service;

import com.ly.fleet_management.entity.Vehicle;
import com.ly.fleet_management.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        System.out.println("Vehicle saved: " + savedVehicle);
        return savedVehicle;
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        System.out.println("Vehicles fetched from DB: " + vehicles);
        return vehicles;
    }
}
