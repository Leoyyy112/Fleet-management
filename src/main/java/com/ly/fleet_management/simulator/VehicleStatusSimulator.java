package com.ly.fleet_management.simulator;

import com.ly.fleet_management.websocket.VehicleStatusHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class VehicleStatusSimulator {

    @Autowired
    private VehicleStatusHandler vehicleStatusHandler;

    @Scheduled(fixedRate = 5000)
    public void simulateVehicleStatus() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String timestamp = sdf.format(new Date());
        String statusMessage = "{ \"vehicleId\": 1, \"status\": \"ACTIVE\", \"timestamp\": \"" + timestamp + "\" }";
        vehicleStatusHandler.sendVehicleStatus(statusMessage);
    }
}
