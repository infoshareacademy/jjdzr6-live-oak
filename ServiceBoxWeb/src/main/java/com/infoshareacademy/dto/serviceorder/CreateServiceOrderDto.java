package com.infoshareacademy.dto.serviceorder;

import com.infoshareacademy.entity.serviceorder.ServiceOrder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link ServiceOrder} entity
 */
@Getter
@Setter
@NoArgsConstructor
public class CreateServiceOrderDto {
    @NotNull(message = "To pole jest wymagane")
    private String orderNumber;
    private boolean onlyNewParts;
    @NotNull(message = "To pole jest wymagane")
    @Min(message = "Niepoprawny koszt naprawy", value = 1)
    private Integer maxCost;
    @NotBlank(message = "To pole jest wymagane")
    private String description;

    public ServiceOrder toServiceOrder() {
        return new ServiceOrder(
                this.orderNumber,
                this.description,
                this.onlyNewParts,
                this.maxCost
        );
    }
}