package com.ly.fleet_management.controller;

import com.ly.fleet_management.entity.DispatchTask;
import com.ly.fleet_management.entity.Vehicle;
import com.ly.fleet_management.service.DispatchService;
import com.ly.fleet_management.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dispatch-tasks")
public class DispatchTaskController {

    @Autowired
    private DispatchService dispatchService;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public List<DispatchTask> getAllDispatchTasks() {
        return dispatchService.getAllDispatchTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispatchTask> getDispatchTaskById(@PathVariable Long id) {
        return dispatchService.getDispatchTaskById(id)
                .map(task -> ResponseEntity.ok(task))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DispatchTask> createDispatchTask(@RequestBody DispatchTask dispatchTask) {
        Vehicle vehicle = vehicleService.getVehicleById(dispatchTask.getVehicle().getId()).orElse(null);
        if (vehicle != null) {
            dispatchTask.setVehicle(vehicle);
            DispatchTask savedTask = dispatchService.saveDispatchTask(dispatchTask);
            return ResponseEntity.ok(savedTask);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DispatchTask> updateDispatchTask(@PathVariable Long id, @RequestBody DispatchTask dispatchTaskDetails) {
        return dispatchService.getDispatchTaskById(id)
                .map(dispatchTask -> {
                    Vehicle vehicle = vehicleService.getVehicleById(dispatchTaskDetails.getVehicle().getId()).orElse(null);
                    if (vehicle != null) {
                        dispatchTask.setTaskDescription(dispatchTaskDetails.getTaskDescription());
                        dispatchTask.setStatus(dispatchTaskDetails.getStatus());
                        dispatchTask.setStartTime(dispatchTaskDetails.getStartTime());
                        dispatchTask.setEndTime(dispatchTaskDetails.getEndTime());
                        dispatchTask.setVehicle(vehicle);
                        DispatchTask updatedTask = dispatchService.saveDispatchTask(dispatchTask);
                        return ResponseEntity.ok(updatedTask);
                    } else {
                        return ResponseEntity.badRequest().<DispatchTask>build();
                    }
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDispatchTask(@PathVariable Long id) {
        return dispatchService.getDispatchTaskById(id)
                .map(task -> {
                    dispatchService.deleteDispatchTask(id);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
