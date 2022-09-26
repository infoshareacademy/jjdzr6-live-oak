package com.infoshareacademy.mappers.serviceorder;

import com.infoshareacademy.dto.serviceOrder.ServiceOrderDto;
import com.infoshareacademy.entity.serviceorder.ServiceOrder;
import org.springframework.stereotype.Service;

@Service
public class ServiceOrderMapper {

    public ServiceOrderDto toDto(ServiceOrder serviceOrder){

        return new ServiceOrderDto(serviceOrder.getId(), serviceOrder.getOrderNumber(), serviceOrder.getCreatedAt(),
                serviceOrder.getFinishedAt(), serviceOrder.getState(), serviceOrder.isOnlyNewParts(), serviceOrder.getMaxCost(), serviceOrder.getDescription(),
                serviceOrder.getVehicle().getPlateNumber(), serviceOrder.getRepairCard().getId());
    }
}
