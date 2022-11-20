package com.infoshareacademy.entity.serviceorder;

import com.infoshareacademy.dto.serviceorder.RepairDto;
import lombok.*;
import org.springframework.security.core.parameters.P;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link Part} entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartDto {
    private Long id;
    @NotBlank(message = "To pole jest wymagane")
    private String partName;
    @NotNull(message = "To pole jest wymagane")
    @Min(message = "Niepoprawny koszt", value = 1)
    private Double cost;
    @NotNull(message = "To pole jest wymagane")
    @Min(message = "Niepoprawna ilość", value = 1)
    private Integer quantity;

    public static PartDto fromPart(Part part) {
        if (part == null) {
            return null;
        }

        return new PartDto(part.getId(), part.getPartName(), part.getCost(), part.getQuantity());
    }

    public Part toPart() {
        return new Part(this.getPartName(), this.getCost(), this.getQuantity());
    }
}