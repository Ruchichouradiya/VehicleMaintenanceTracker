package com.example.maintenancehistorytservice;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.maintenancehistorytservice.model.MaintenanceHistory;
import com.example.maintenancehistorytservice.repository.MaintenanceHistoryRepository;
import com.example.maintenancehistorytservice.service.MaintenanceHistoryService;


public class MaintenanceHistoryServiceTest {

    @Mock
    private MaintenanceHistoryRepository maintenanceRepository;

    @InjectMocks
    private MaintenanceHistoryService maintenanceService;

	private MaintenanceHistory history;

	private void prepareData() {

		// Mock data
		history = new MaintenanceHistory();
		history.setDescription("Oil Change");
		history.setCost(new BigDecimal(2.5));
		history.setServiceDate(LocalDate.now());
		history.setVehicleId(1L);

	}

	public MaintenanceHistoryServiceTest() {
		MockitoAnnotations.openMocks(this);
	}

    @Test
    public void testAddServiceHistory() {
    	
    	prepareData();

        Mockito.when(maintenanceRepository.save(Mockito.any(MaintenanceHistory.class))).thenAnswer(invocation -> {
        	MaintenanceHistory createdServiceHistory = invocation.getArgument(0);
			createdServiceHistory.setId(1L); // Assign a mock ID
			return createdServiceHistory;
		});
        
        //Mockito.when(maintenanceRepository.save(history)).thenReturn(createdServiceHistory);

        MaintenanceHistory createdHistory = maintenanceService.addServiceHistory(history);
        Assert.assertEquals("Oil Change", createdHistory.getDescription());  
   		Assert.assertNotNull(createdHistory.getId());
    }

    @Test
    public void testUpdateServiceHistory() {
        Long serviceHistoryId = 1L;
       
        prepareData();
        MaintenanceHistory updatedServiceHistory = history;
        updatedServiceHistory.setDescription("Brake Inspection");
        updatedServiceHistory.setId(serviceHistoryId);

        Mockito.when(maintenanceRepository.existsById(serviceHistoryId)).thenReturn(true);
        Mockito.when(maintenanceRepository.save(updatedServiceHistory)).thenReturn(updatedServiceHistory);

        MaintenanceHistory result = maintenanceService.updateServiceHistory(serviceHistoryId, updatedServiceHistory);

        Mockito.verify(maintenanceRepository).existsById(serviceHistoryId);
        Mockito.verify(maintenanceRepository).save(updatedServiceHistory);

        Assert.assertEquals("Brake Inspection", result.getDescription());
    }

    @Test
    public void testUpdateServiceHistoryNotFound() {
        Long nonExistingServiceHistoryId = 999L;
        prepareData();
        Mockito.when(maintenanceRepository.existsById(nonExistingServiceHistoryId)).thenReturn(false);

        Assert.assertThrows(RuntimeException.class,
                () -> maintenanceService.updateServiceHistory(nonExistingServiceHistoryId, history));

        Mockito.verify(maintenanceRepository).existsById(nonExistingServiceHistoryId);
        Mockito.verify(maintenanceRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void testDeleteServiceHistory() {
        Long serviceHistoryId = 1L;

        maintenanceService.deleteServiceHistory(serviceHistoryId);

        Mockito.verify(maintenanceRepository).deleteById(serviceHistoryId);
    }
}
