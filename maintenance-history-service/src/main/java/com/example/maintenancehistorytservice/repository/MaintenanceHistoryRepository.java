package com.example.maintenancehistorytservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.maintenancehistorytservice.model.MaintenanceHistory;

public interface MaintenanceHistoryRepository extends JpaRepository<MaintenanceHistory, Long> {
    List<MaintenanceHistory> findByVehicleId(Long vehicleId);
}