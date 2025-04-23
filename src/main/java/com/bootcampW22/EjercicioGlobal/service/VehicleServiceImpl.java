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
import java.util.Optional;
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
            vehicleRepository.addVehicle(dtoToEntity(vehicleDto));
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
    @Override
    public List<VehicleDto> searchByBrandAndRangeYear(String brand, Integer start_year, Integer end_year) {
        List<Vehicle> listVehicle = vehicleRepository.findByBrandAndRangeYear(brand,start_year,end_year);
        if(listVehicle.isEmpty()){
            throw new NotFoundException("No se encontraron vehículos con esos criterios.");
        }
        return listVehicle.stream().map(x -> objectMapper.convertValue(x,VehicleDto.class)).toList();
    }
    @Override
    public Double averageSpeedByBrand(String brand) {
        List<Vehicle> listByBrand = vehicleRepository.findByBrand(brand);
        if(listByBrand.isEmpty()) {
            throw new NotFoundException("No se encontraron vehículos de esa marca.");
        }
        return listByBrand.stream().mapToInt(x -> Integer.parseInt(x.getMax_speed())).average().getAsDouble();
    }
    @Override
    public ResponseDto updateSpeed(Long id, String speed) {
      Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new NotFoundException("No se encontró el vehículo."));
        vehicleRepository.updateSpeed(vehicle, speed);
        return new ResponseDto(" Velocidad del vehículo actualizada exitosamente.");
    }

    @Override
    public ResponseDto addMassiveVehicle(List<VehicleDto> listVehicleDto) {
        for(VehicleDto vehicleDto :listVehicleDto){
            if(!vehicleRepository.exist(vehicleDto.getId())){
                vehicleRepository.addVehicle(objectMapper.convertValue(vehicleDto,Vehicle.class));
            }else{
                throw new ConflictException("Algún vehículo tiene un identificador ya existente.");
            }
        }
        return new ResponseDto("Vehículos creados exitosamente.");
    }

    @Override
    public List<VehicleDto> findFuelByType(String type) {
        List<Vehicle> listFilter = vehicleRepository.findByFuel(type);
         if(listFilter.isEmpty())
         {
             throw new NotFoundException("No se encontraron vehículos con ese tipo de combustible.");
         }
        return listFilter.stream()
                .map(x -> objectMapper.convertValue(x,VehicleDto.class))
                .toList();
    }

    @Override
    public ResponseDto deleteVehicle(Long id) {
       Vehicle vehicle = vehicleRepository.findById(id)
               .orElseThrow(() -> new NotFoundException("No se encontró el vehículo."));
        vehicleRepository.deleteVehicle(vehicle);
        return new ResponseDto("Vehículo eliminado exitosamente.");
    }

    @Override
    public List<VehicleDto> searchByTransmission(String type) {
        List<Vehicle> filterList = vehicleRepository.findByTransmission(type);
        if(filterList.isEmpty()){
            throw new NotFoundException("No se encontraron vehículos con ese tipo de transmisión.");
        }
        return filterList.stream().map(x -> objectMapper.convertValue(x,VehicleDto.class)).toList();
    }

    @Override
    public ResponseDto updateFuel(Long id, String fuel) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró el vehículo."));
        vehicleRepository.updateFuel(vehicle,fuel);
        return new ResponseDto("Tipo de combustible del vehículo actualizado exitosamente.");
    }

    @Override
    public Double averageCapacityByBrand(String brand) {
        List<Vehicle> listByBrand = vehicleRepository.searchByBrand(brand);
        if(listByBrand.isEmpty()){
            throw new NotFoundException("No se encontraron vehículos de esa marca.");
        }
        return listByBrand.stream().mapToInt(x -> x.getPassengers()).average().getAsDouble();
    }

    @Override
    public List<VehicleDto> searchByDimensions(String length, String width) {
        String[] lengthRange = length.split("-",2);
        Double minLength = Double.parseDouble(lengthRange[0]);
        Double maxLength = Double.parseDouble(lengthRange[1]);

        String[] widthRange = width.split("-",2);
        Double minWidth = Double.parseDouble(widthRange[0]);
        Double maxWidth = Double.parseDouble(widthRange[1]);

        List<Vehicle> filterList =vehicleRepository.searchByDimensions(minLength,maxLength,minWidth,maxWidth);
        if(filterList.isEmpty()){
            throw new NotFoundException("No se encontraron vehículos con esas dimensiones.");
        }
        return filterList.stream().map(x -> objectMapper.convertValue(x,VehicleDto.class)).toList();
    }
}
