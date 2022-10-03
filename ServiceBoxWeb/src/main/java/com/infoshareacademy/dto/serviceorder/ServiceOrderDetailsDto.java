package com.infoshareacademy.dto.serviceorder;

import com.infoshareacademy.entity.serviceorder.ServiceOrder;
import com.infoshareacademy.entity.serviceorder.ServiceOrderState;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link com.infoshareacademy.entity.serviceorder.ServiceOrder} entity
 */
@AllArgsConstructor
@Getter
public class ServiceOrderDetailsDto {
    private final Long id;
    private final String orderNumber;
    private final LocalDateTime createdAt;
    private final LocalDateTime finishedAt;
    private final ServiceOrderState state;
    private final boolean onlyNewParts;
    private final Integer maxCost;
    private final String description;
    private final Long vehicleId;
    private final List<NotesDto> notes;

    /**
     * A DTO for the {@link com.infoshareacademy.entity.serviceorder.Notes} entity
     */
    @AllArgsConstructor
    @Getter
    public static class NotesDto {
        private final Long id;
        private final LocalDateTime createdAt;
        private final String note;
    }

    public static ServiceOrderDetailsDto fromServiceOrder(ServiceOrder serviceOrder) {
        if (serviceOrder == null) {
            return null;
        }

        List<NotesDto> orderNotes = new ArrayList<>();
        if (serviceOrder.getNotes() != null) {
            orderNotes = serviceOrder.getNotes().stream()
                    .map(note -> new NotesDto(
                            note.getId(),
                            note.getCreatedAt(),
                            note.getNote()
                    )).toList();
        }

        return new ServiceOrderDetailsDto(
                serviceOrder.getId(),
                serviceOrder.getOrderNumber(),
                serviceOrder.getCreatedAt(),
                serviceOrder.getFinishedAt(),
                serviceOrder.getState(),
                serviceOrder.isOnlyNewParts(),
                serviceOrder.getMaxCost(),
                serviceOrder.getDescription(),
                serviceOrder.getVehicle().getId(),
                orderNotes
        );
    }
}