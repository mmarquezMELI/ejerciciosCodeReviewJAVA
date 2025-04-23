package com.bootcampW22.EjercicioGlobal.service;

import com.bootcampW22.EjercicioGlobal.dto.ResponseDto;
import com.bootcampW22.EjercicioGlobal.dto.VehicleDto;
import com.bootcampW22.EjercicioGlobal.entity.Vehicle;
import com.bootcampW22.EjercicioGlobal.exception.ConflictException;
import com.bootcampW22.EjercicioGlobal.exception.NotFoundException;
import com.bootcampW22.EjercicioGlobal.repository.IVehicleRepository;
import com.bootcampW22.EjercicioGlobal.repository.VehicleRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements IVehicleService{

    IVehicleRepository vehicleRepository;
    final ObjectMapper objectMapper;

    public VehicleServiceImpl(VehicleRepositoryImpl vehicleRepository, ObjectMapper objectMapper){
        this.vehicleRepository = vehicleRepository;
        this.objectMapper = objectMapper;
    }
    @Override
    public List<VehicleDto> searchAllVehicles() {
        ObjectMapper mapper = new ObjectMapper();
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        if(vehicleList.isEmpty()){
            throw new NotFoundException("No se encontró ningun auto en el sistema.");
        }
        return vehicleList.stream()
                .map(v -> mapper.convertValue(v,VehicleDto.class))
                .collect(Collectors.toList());
    }

    private Vehicle dtoToEntity(VehicleDto vehicledto)
    {
     return objectMapper.convertValue(vehicledto,Vehicle.class);
    }

    private VehicleDto entityToDto(Vehicle vehicle){
        return objectMapper.convertValue(vehicle,VehicleDto.class);
    }
    @Override
    public ResponseDto addVehicle(VehicleDto vehicleDto) {
        if(vehicleRepository.findById(vehicleDto.getId()).isPresent()){
            throw new ConflictException("Identificador del vehículo ya existente.");
        }
        try{
            vehicleRepository.addVehicle(dtoToEntity(vehicleDto));
        }catch (Exception e){
            e.getMessage();
        }
        return new ResponseDto("Vehículo creado exitosamente.");
    }

    @Override
    public List<VehicleDto> searchByColorAndYear(String color, Integer year) {
        List<Vehicle>  listVehicle = vehicleRepository.findByColorAndYear(color,year);
        if(listVehicle.isEmpty()) {
            throw new NotFoundException("No se encontraron vehículos con esos criterios.");
        }
       return listVehicle.stream().map(x -> entityToDto(x)).toList();
    }
}
