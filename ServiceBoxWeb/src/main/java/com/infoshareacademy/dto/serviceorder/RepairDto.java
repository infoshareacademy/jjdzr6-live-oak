package com.infoshareacademy.dto.serviceorder;

import com.infoshareacademy.entity.serviceorder.Repair;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link com.infoshareacademy.entity.serviceorder.Repair} entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepairDto {
    private Long id;
    @NotBlank(message = "To pole jest wymagane")
    private String description;
    @NotNull(message = "To pole jest wymagane")
    @Min(message = "Niepoprawny koszt", value = 1)
    private Double cost;

    public static RepairDto fromRepair(Repair repair) {
        if (repair == null) {
            return null;
        }

        return new RepairDto(repair.getId(), repair.getDescription(), repair.getCost());
    }

    public Repair toRepair() {
        return new Repair(this.getDescription(), this.getCost());
    }
}