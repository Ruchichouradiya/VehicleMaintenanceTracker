package com.example.vehiclemgmtservice;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.vehiclemgmtservice.model.Vehicle;
import com.example.vehiclemgmtservice.repository.VehicleRepository;
import com.example.vehiclemgmtservice.service.VehicleService;

import java.util.Optional;

public class VehicleServiceTest {

	@Mock
	private VehicleRepository vehicleRepository;

	@InjectMocks
	private VehicleService vehicleService;

	private Vehicle vehicle;

	@Before
	private void prepareData() {

		// Mock data
		vehicle = new Vehicle();
		vehicle.setMake("Honda");
		vehicle.setModel("Civic");
		vehicle.setYear(2023);
		vehicle.setLicensePlate("214ABC");

	}

	public VehicleServiceTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreateVehicle() {

		//prepareData();
		Mockito.when(vehicleRepository.save(Mockito.any(Vehicle.class))).thenAnswer(invocation -> {
			Vehicle savedVehicle = invocation.getArgument(0);
			savedVehicle.setId(1L); // Assign a mock ID
			return savedVehicle;
		});

		// Test vehicle creation
		Vehicle createdVehicle = vehicleService.addVehicle(vehicle);

		Mockito.verify(vehicleRepository).save(vehicle);

		// Assert that the created vehicle has a non-null ID
		Assert.assertNotNull(createdVehicle.getId());
	}

	@Test
	public void testGetVehicleById() {
		long vehicleId = 1L;
		//prepareData();
		// Mock repository behavior
		Mockito.when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));

		// Test service method
		Optional<Vehicle> result = vehicleService.getVehicleById(vehicleId);

		// Verify repository interaction
		Mockito.verify(vehicleRepository).findById(vehicleId);

		// Assert the result
		Assert.assertTrue(result.isPresent());
	}

	@Test
	public void testGetVehicleByIdNotFound() {
		// Mock data
		long nonExistingVehicleId = 999L;

		// Mock repository behavior
		Mockito.when(vehicleRepository.findById(nonExistingVehicleId)).thenReturn(Optional.empty());

		// Test service method
		Optional<Vehicle> result = vehicleService.getVehicleById(nonExistingVehicleId);

		// Verify repository interaction
		Mockito.verify(vehicleRepository).findById(nonExistingVehicleId);

		// Assert the result
		Assert.assertFalse(result.isPresent());
	}

	@Test
	public void testUpdateVehicle() {
		// Mock data
		long vehicleId = 1L;
		//prepareData();
		Vehicle updatedVehicle = vehicle;
		updatedVehicle.setModel("City");

		// Mock repository behavior
		Mockito.when(vehicleRepository.existsById(vehicleId)).thenReturn(true);
		Mockito.when(vehicleRepository.save(updatedVehicle)).thenReturn(updatedVehicle);

		// Test service method
		Vehicle result = vehicleService.updateVehicle(vehicleId, updatedVehicle);

		// Verify repository interaction
		Mockito.verify(vehicleRepository).existsById(vehicleId);
		Mockito.verify(vehicleRepository).save(updatedVehicle);

		// Assert the result
		Assert.assertEquals("City", result.getModel());
	}

	@Test
	public void testDeleteVehicle() {
		// Mock data
		long vehicleId = 1L;

		// Test service method
		vehicleService.deleteVehicle(vehicleId);

		// Verify repository interaction
		Mockito.verify(vehicleRepository).deleteById(vehicleId);
	}

}
