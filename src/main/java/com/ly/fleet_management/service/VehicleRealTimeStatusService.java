package com.ly.fleet_management.service;

import com.ly.fleet_management.entity.VehicleRealTimeStatus;
import com.ly.fleet_management.repository.VehicleRealTimeStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleRealTimeStatusService {
    @Autowired
    private VehicleRealTimeStatusRepository vehicleRealTimeStatusRepository;

    public VehicleRealTimeStatus saveVehicleRealTimeStatus(VehicleRealTimeStatus vehicleRealTimeStatus) {
        return vehicleRealTimeStatusRepository.save(vehicleRealTimeStatus);
    }
}
