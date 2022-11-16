package com.infoshareacademy.service;

import com.infoshareacademy.dto.serviceorder.RepairDto;
import com.infoshareacademy.entity.serviceorder.PartDto;
import com.infoshareacademy.entity.serviceorder.RepairCard;
import com.infoshareacademy.entity.serviceorder.ServiceOrder;
import com.infoshareacademy.repository.RepairCardRepository;
import com.infoshareacademy.repository.ServiceOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RepairCardService {
    public final RepairCardRepository repairCardRepository;
    public final ServiceOrderRepository serviceOrderRepository;

    @Transactional
    public RepairCard getRepairCardByServiceOrderId(Long serviceOrderId) {
        Optional<ServiceOrder> serviceOrderOptional = serviceOrderRepository.findById(serviceOrderId);

        if (serviceOrderOptional.isEmpty()) {
            return null;
        }

        if (serviceOrderOptional.get().getRepairCard() != null) {
            // return existing repair card
            return serviceOrderOptional.get().getRepairCard();
        }

        // create repair card if not exists
        RepairCard repairCard = new RepairCard();
        repairCardRepository.save(repairCard);

        ServiceOrder serviceOrder = serviceOrderOptional.get();
        serviceOrder.setRepairCard(repairCard);
        repairCard.setServiceOrder(serviceOrderOptional.get());
        serviceOrderRepository.save(serviceOrder);

        return repairCard;
    }

    @Transactional
    public void addRepair(Long serviceOrderId, RepairDto repairDto) {
        Optional<ServiceOrder> serviceOrderOptional = serviceOrderRepository.findById(serviceOrderId);

        if (serviceOrderOptional.isPresent()) {
            RepairCard repairCard = serviceOrderOptional.get().getRepairCard();
            repairCard.getRepairs().add(repairDto.toRepair());
            repairCardRepository.save(repairCard);
        }
    }

    @Transactional
    public void addPart(Long serviceOrderId, PartDto partDto) {
        Optional<ServiceOrder> serviceOrderOptional = serviceOrderRepository.findById(serviceOrderId);

        if (serviceOrderOptional.isPresent()) {
            RepairCard repairCard = serviceOrderOptional.get().getRepairCard();
            repairCard.getParts().add(partDto.toPart());
            repairCardRepository.save(repairCard);
        }
    }
 }
