package com.example.maintenancereminderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.maintenancereminderservice.model.MaintenanceReminder;

@Repository
public interface MaintenanceReminderRepository extends JpaRepository<MaintenanceReminder, Long> {
    // Additional query methods if needed
}
