package com.example.maintenancereminderservice;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.maintenancereminderservice.model.MaintenanceReminder;
import com.example.maintenancereminderservice.repository.MaintenanceReminderRepository;
import com.example.maintenancereminderservice.service.MaintenanceReminderService;

public class MaintenanceReminderServiceTest {

    @Mock
    private MaintenanceReminderRepository reminderRepository;

    @InjectMocks
    private MaintenanceReminderService reminderService;

    private MaintenanceReminder reminder;
    

	public MaintenanceReminderServiceTest() {
		MockitoAnnotations.openMocks(this);
	}
	
    @Test
    public void testCreateReminder() {
        prepareData();

        Mockito.when(reminderRepository.save(Mockito.any(MaintenanceReminder.class))).thenAnswer(invocation -> {
        	MaintenanceReminder createdReminder = invocation.getArgument(0);
        	createdReminder.setId(1L); // Assign a mock ID
			return createdReminder;
		});

        MaintenanceReminder createdReminder = reminderService.createReminder(reminder);
        Assert.assertEquals("Oil Change", createdReminder.getDescription());  
   		Assert.assertNotNull(createdReminder.getId());
    }

    @Test
    public void testDeleteReminder() {
        Long reminderId = 1L;

        reminderService.deleteReminder(reminderId);

        Mockito.verify(reminderRepository).deleteById(reminderId);
    }

	private void prepareData() {

		// Mock data
		reminder = new MaintenanceReminder();
		reminder.setDescription("Oil Change");
		reminder.setDueDate(LocalDate.now());
		reminder.setVehicleId(1L);

	}
}
