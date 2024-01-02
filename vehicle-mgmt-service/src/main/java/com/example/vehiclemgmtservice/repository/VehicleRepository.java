package com.example.vehiclemgmtservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vehiclemgmtservice.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    // You can add custom query methods if needed
}
