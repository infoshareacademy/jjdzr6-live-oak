package com.infoshareacademy.mappers.vehicle;

import com.infoshareacademy.dao.client.ClientDao;
import com.infoshareacademy.dto.vehicle.VehicleDto;
import com.infoshareacademy.entity.client.Client;
import com.infoshareacademy.entity.vehicle.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleMapper {
    private final ClientDao clientDao;

    public VehicleDto toDto(Vehicle vehicle) {
        return new VehicleDto(
                vehicle.getId(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getPlateNumber(),
                vehicle.getEngineCapacity(),
                vehicle.getProductionYear(),
                vehicle.getMileage(),
                vehicle.getVin(),
                vehicle.getClient().getName(),
                vehicle.getClient().getId()
        );
    }

    public Vehicle fromDto(VehicleDto vehicleDto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake(vehicleDto.getMake());
        vehicle.setModel(vehicleDto.getModel());
        vehicle.setPlateNumber(vehicleDto.getPlateNumber());
        vehicle.setEngineCapacity(vehicleDto.getEngineCapacity());
        vehicle.setProductionYear(vehicleDto.getProductionYear());

        Client client = clientDao.find(vehicleDto.getClientId());
        vehicle.setClient(client);
        return vehicle;
    }
}
