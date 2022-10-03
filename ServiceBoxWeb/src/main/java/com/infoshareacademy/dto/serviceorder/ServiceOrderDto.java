package com.infoshareacademy.dto.serviceorder;

import com.infoshareacademy.entity.serviceorder.ServiceOrderState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceOrderDto {
    private Long id;
    @NotBlank(message = "To pole jest wymagane")
    private String orderNumber;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;
    private ServiceOrderState state = ServiceOrderState.CREATED;
    private boolean onlyNewParts;
    @NotNull(message = "To pole jest wymagane")
    @Min(message = "Niepoprawny koszt naprawy", value = 1)
    private Integer maxCost;
    @NotBlank(message = "To pole jest wymagane")
    private String description;
    @NotBlank(message = "To pole jest wymagane")
    private String plateNumber;
    private Long cardId;
    private String clientName;
    private String clientPhone;
    private Long vehicleId;
}
