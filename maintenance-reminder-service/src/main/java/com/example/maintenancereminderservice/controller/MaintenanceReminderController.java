package com.example.maintenancereminderservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.maintenancereminderservice.model.MaintenanceReminder;
import com.example.maintenancereminderservice.service.MaintenanceReminderService;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
public class MaintenanceReminderController {

    private final MaintenanceReminderService reminderService;

    @Autowired
    public MaintenanceReminderController(MaintenanceReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @GetMapping
    public List<MaintenanceReminder> getAllReminders() {
        return reminderService.getAllReminders();
    }

    @PostMapping
    public ResponseEntity<MaintenanceReminder> createReminder(@RequestBody MaintenanceReminder reminder) {
        MaintenanceReminder createdReminder = reminderService.createReminder(reminder);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReminder);
    }

    @DeleteMapping("/{reminderId}")
    public ResponseEntity<Void> deleteReminder(@PathVariable Long reminderId) {
        reminderService.deleteReminder(reminderId);
        return ResponseEntity.noContent().build();
    }

    // Additional methods for updating, querying, etc.
}
