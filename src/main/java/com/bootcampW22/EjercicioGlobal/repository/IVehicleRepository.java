package com.bootcampW22.EjercicioGlobal.repository;

import com.bootcampW22.EjercicioGlobal.entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface IVehicleRepository {
    List<Vehicle> findAll();

    void addVehicle(Vehicle vehicle);

    List<Vehicle> findByColorAndYear(String color, Integer year);

    List<Vehicle> findByBrandAndRangeYear(String brand, Integer start_year, Integer end_year);

    List<Vehicle> findByBrand(String brand);

    Optional<Vehicle> findById(Long id);

    void updateSpeed(Vehicle vehicle, String speed);
    

    List<Vehicle> findByFuel(String type);

    Boolean exist(Long id);

    void deleteVehicle(Vehicle vehicle);

    List<Vehicle> findByTransmission(String transmission);

    void updateFuel(Vehicle vehicle, String fuel);

    List<Vehicle> searchByBrand(String brand);
}
