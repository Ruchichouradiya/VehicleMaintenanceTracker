package com.example.maintenancereminderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.maintenancereminderservice.model.MaintenanceReminder;
import com.example.maintenancereminderservice.repository.MaintenanceReminderRepository;
import java.util.List;

@Service
public class MaintenanceReminderService {

    private final MaintenanceReminderRepository reminderRepository;

    @Autowired
    public MaintenanceReminderService(MaintenanceReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    public List<MaintenanceReminder> getAllReminders() {
        return reminderRepository.findAll();
    }

    public MaintenanceReminder createReminder(MaintenanceReminder reminder) {
        return reminderRepository.save(reminder);
    }

    public void deleteReminder(Long reminderId) {
        reminderRepository.deleteById(reminderId);
    }

    // Additional methods for updating, querying, etc.
}
