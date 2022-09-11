package com.infoshareacademy.controller.employee;

import com.infoshareacademy.entity.Vehicle;
import com.infoshareacademy.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/employee")
@Controller
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/vehicles")
    public String getVehicles(Model model) {
        model.addAttribute("vehicles", vehicleService.findAll());
        return "employee/vehicle-list";
    }

    @GetMapping("/vehicles/add")
    public String addNewVehicle(Model model) {
        model.addAttribute("newVehicle", new Vehicle());
        return "employee/vehicle-add";
    }

    @PostMapping("/vehiclesadd")
    public String addNewVehicle(@Valid @ModelAttribute("newVehicle") Vehicle vehicle, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employee/vehicle-add";
        }

        vehicleService.addVehicle(vehicle);
        return "redirect:/employee/vehicles";
    }
}

