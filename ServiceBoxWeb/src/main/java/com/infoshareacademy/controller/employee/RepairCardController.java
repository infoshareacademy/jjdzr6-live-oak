package com.infoshareacademy.controller.employee;

import com.infoshareacademy.dto.serviceorder.RepairDto;
import com.infoshareacademy.entity.serviceorder.PartDto;
import com.infoshareacademy.entity.serviceorder.Repair;
import com.infoshareacademy.entity.serviceorder.RepairCard;
import com.infoshareacademy.service.RepairCardService;
import com.infoshareacademy.service.ServiceOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
public class RepairCardController {
    public final RepairCardService repairCardService;
    public final ServiceOrderService serviceOrderService;

    @GetMapping("/service-orders/{id}/repair-card")
    public String updateRepairCard(@PathVariable Long id, Model model) {
        RepairCard repairCard = repairCardService.getRepairCardByServiceOrderId(id);

        model.addAttribute("repair", new RepairDto());
        model.addAttribute("part", new PartDto());
        model.addAttribute("repairCard", repairCard);

        return "employee/repair-card";
    }

    @PostMapping("/service-orders/{id}/repair")
    public String addRepair(@Valid @ModelAttribute("repair") RepairDto repairDto,
                            BindingResult bindingResult,
                            @PathVariable Long id,
                            Model model
    ) {
        if (bindingResult.hasErrors()) {
            RepairCard repairCard = repairCardService.getRepairCardByServiceOrderId(id);

            model.addAttribute("repair", repairDto);
            model.addAttribute("part", new PartDto());
            model.addAttribute("repairCard", repairCard);
            return "employee/repair-card";
        }

        repairCardService.addRepair(id, repairDto);
        return "redirect:/employee/service-orders/" + id + "/repair-card";
    }

    @PostMapping("/service-orders/{id}/part")
    public String addPart(@Valid @ModelAttribute("part") PartDto partDto,
                            BindingResult bindingResult,
                            @PathVariable Long id,
                            Model model
    ) {
        if (bindingResult.hasErrors()) {
            RepairCard repairCard = repairCardService.getRepairCardByServiceOrderId(id);

            model.addAttribute("part", partDto);
            model.addAttribute("repair", new Repair());
            model.addAttribute("repairCard", repairCard);
            return "employee/repair-card";
        }

        repairCardService.addPart(id, partDto);
        return "redirect:/employee/service-orders/" + id + "/repair-card";
    }
}
