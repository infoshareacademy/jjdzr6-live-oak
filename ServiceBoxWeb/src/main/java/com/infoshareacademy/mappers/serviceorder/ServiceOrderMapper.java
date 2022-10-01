package com.infoshareacademy.mappers.serviceorder;

import com.infoshareacademy.dao.vehicle.VehicleDao;
import com.infoshareacademy.dto.serviceorder.ServiceOrderDto;
import com.infoshareacademy.entity.client.Client;
import com.infoshareacademy.entity.serviceorder.RepairCard;
import com.infoshareacademy.entity.serviceorder.ServiceOrder;
import com.infoshareacademy.entity.vehicle.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceOrderMapper {
    private final VehicleDao vehicleDao;

    public ServiceOrderDto toDto(ServiceOrder serviceOrder){
        ServiceOrderDto serviceOrderDto = new ServiceOrderDto();

        serviceOrderDto.setId(serviceOrder.getId());
        serviceOrderDto.setOrderNumber(serviceOrder.getOrderNumber());
        serviceOrderDto.setCreatedAt(serviceOrder.getCreatedAt());
        serviceOrderDto.setFinishedAt(serviceOrder.getFinishedAt());
        serviceOrderDto.setState(serviceOrder.getState());
        serviceOrderDto.setOnlyNewParts(serviceOrder.isOnlyNewParts());
        serviceOrderDto.setMaxCost(serviceOrder.getMaxCost());
        serviceOrderDto.setDescription(serviceOrder.getDescription());
        serviceOrderDto.setPlateNumber(serviceOrder.getVehicle().getPlateNumber());
        serviceOrderDto.setVehicleId(serviceOrder.getVehicle().getId());

        Client client = serviceOrder.getVehicle().getClient();
        serviceOrderDto.setClientName(client.getName());
        serviceOrderDto.setClientPhone(client.getPhoneNumber());

        RepairCard repairCard = serviceOrder.getRepairCard();
        if (repairCard != null) {
            serviceOrderDto.setCardId(repairCard.getId());
        }

        return serviceOrderDto;
    }

    public ServiceOrder fromDto(ServiceOrderDto serviceOrderDto) {
        ServiceOrder serviceOrder = new ServiceOrder();

        serviceOrder.setOrderNumber(serviceOrderDto.getOrderNumber());
        serviceOrder.setDescription(serviceOrderDto.getDescription());
        serviceOrder.setOnlyNewParts(serviceOrderDto.isOnlyNewParts());
        serviceOrder.setMaxCost(serviceOrderDto.getMaxCost());

        Vehicle vehicle = vehicleDao.find(serviceOrderDto.getVehicleId());
        serviceOrder.setVehicle(vehicle);

        return serviceOrder;
    }
}
