package com.example.maintenancehistorytservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.maintenancehistorytservice.model.MaintenanceHistory;
import com.example.maintenancehistorytservice.service.MaintenanceHistoryService;
import java.util.List;


@RestController
@RequestMapping("/api/Maintenance-history")
public class MaintenanceHistoryController {

    private final MaintenanceHistoryService maintenanceHistoryService;

    @Autowired
    public MaintenanceHistoryController(MaintenanceHistoryService maintenanceHistoryService) {
        this.maintenanceHistoryService = maintenanceHistoryService;
    }

    @GetMapping("/{vehicleId}")
    public List<MaintenanceHistory> getServiceHistoryByVehicleId(@PathVariable Long vehicleId) {
        return maintenanceHistoryService.getServiceHistoryByVehicleId(vehicleId);
    }

    @PostMapping
    public ResponseEntity<MaintenanceHistory> addServiceHistory(@RequestBody MaintenanceHistory serviceHistory) {
        MaintenanceHistory addedServiceHistory = maintenanceHistoryService.addServiceHistory(serviceHistory);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedServiceHistory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceHistory> updateServiceHistory(@PathVariable Long id, @RequestBody MaintenanceHistory updatedServiceHistory) {
        try {
            MaintenanceHistory updated = maintenanceHistoryService.updateServiceHistory(id, updatedServiceHistory);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceHistory(@PathVariable Long id) {
    	maintenanceHistoryService.deleteServiceHistory(id);
        return ResponseEntity.noContent().build();
    }
}