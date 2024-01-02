package com.example.maintenancehistorytservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.maintenancehistorytservice.model.MaintenanceHistory;
import com.example.maintenancehistorytservice.repository.MaintenanceHistoryRepository;

@Service
public class MaintenanceHistoryService {

    private final MaintenanceHistoryRepository maintenanceRepository;

    @Autowired
    public MaintenanceHistoryService(MaintenanceHistoryRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    public List<MaintenanceHistory> getServiceHistoryByVehicleId(Long vehicleId) {
        return maintenanceRepository.findByVehicleId(vehicleId);
    }

    public MaintenanceHistory addServiceHistory(MaintenanceHistory serviceHistory) {
        return maintenanceRepository.save(serviceHistory);
    }

    public MaintenanceHistory updateServiceHistory(Long id, MaintenanceHistory updatedServiceHistory) {
        if (maintenanceRepository.existsById(id)) {
            updatedServiceHistory.setId(id);
            return maintenanceRepository.save(updatedServiceHistory);
        } else {
            throw new RuntimeException("Service history record not found with id: " + id);
        }
    }

    public void deleteServiceHistory(Long id) {
    	maintenanceRepository.deleteById(id);
    }
}