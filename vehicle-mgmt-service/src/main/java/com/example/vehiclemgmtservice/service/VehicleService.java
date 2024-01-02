package com.example.vehiclemgmtservice.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vehiclemgmtservice.model.Vehicle;
import com.example.vehiclemgmtservice.repository.VehicleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {
        if (vehicleRepository.existsById(id)) {
            updatedVehicle.setId(id);
            return vehicleRepository.save(updatedVehicle);
        } else {
            // Handle the case where the vehicle with the given id is not found
            throw new RuntimeException("Vehicle not found with id: " + id);
        }
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}
