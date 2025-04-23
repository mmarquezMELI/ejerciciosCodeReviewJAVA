package com.bootcampW22.EjercicioGlobal.repository;

import com.bootcampW22.EjercicioGlobal.entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface IVehicleRepository {
    List<Vehicle> findAll();

    void addVehicle(Vehicle vehicle);

    Optional<Vehicle> findById(Long id);

    List<Vehicle> findByColorAndYear(String color, Integer year);
}
