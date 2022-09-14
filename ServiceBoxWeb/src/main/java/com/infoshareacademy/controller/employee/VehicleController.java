package com.infoshareacademy.controller.employee;

import com.infoshareacademy.entity.Vehicle;
import com.infoshareacademy.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/employee/vehicles")
@Controller
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public String getVehicles(Model model, @RequestParam(name = "search", required = false, defaultValue = "") String searchQuery) {
        if (searchQuery.isBlank()) {
            model.addAttribute("vehicles", vehicleService.findAll());
        } else {
            model.addAttribute("vehicles", vehicleService.findByQuery(searchQuery));
            model.addAttribute("search", searchQuery);
        }

        return "employee/vehicle-list";
    }

    @GetMapping("add")
    public String addNewVehicle(Model model) {
        model.addAttribute("newVehicle", new Vehicle());
        return "employee/vehicle-add";
    }

    @PostMapping("add")
    public String addNewVehicle(@Valid @ModelAttribute("newVehicle") Vehicle vehicle, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employee/vehicle-add";
        }

        vehicleService.addVehicle(vehicle);
        return "redirect:/employee/vehicles";
    }
}

