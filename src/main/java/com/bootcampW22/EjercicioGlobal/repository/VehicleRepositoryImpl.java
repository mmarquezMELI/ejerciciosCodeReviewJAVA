package com.bootcampW22.EjercicioGlobal.repository;

import com.bootcampW22.EjercicioGlobal.entity.Vehicle;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VehicleRepositoryImpl implements IVehicleRepository {

    private List<Vehicle> listOfVehicles = new ArrayList<>();

    public VehicleRepositoryImpl() throws IOException {
        loadDataBase();
    }

    @Override
    public List<Vehicle> findAll() {
        return listOfVehicles;
    }

    private void loadDataBase() throws IOException {
        File file;
        ObjectMapper objectMapper = new ObjectMapper();
        List<Vehicle> vehicles;

        file = ResourceUtils.getFile("classpath:vehicles_100.json");
        vehicles = objectMapper.readValue(file, new TypeReference<List<Vehicle>>() {
        });

        listOfVehicles = vehicles;
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        listOfVehicles.add(vehicle);
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return listOfVehicles.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    @Override
    public List<Vehicle> findByColorAndYear(String color, Integer year) {
        return listOfVehicles.stream().filter(x -> x.getColor().equals(color) && x.getYear() == year).toList();
    }

    @Override
    public List<Vehicle> findByBrandAndRangeYear(String brand, Integer start_year, Integer end_year) {
        return listOfVehicles.stream()
                .filter(x -> x.getBrand().equals(brand) &&
                        (x.getYear() >= start_year && x.getYear() <= end_year))
                .toList();
    }

    @Override
    public List<Vehicle> findByBrand(String brand) {
        return listOfVehicles.stream().filter(x -> x.getBrand().equals(brand)).toList();
    }
}
