package com.infoshareacademy.controller.employee;

import com.infoshareacademy.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/employee")
@Controller
public class VehicleController {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


    @GetMapping("/vehicles")
    public String getVehicles(Model model) {
        model.addAttribute("vehicles", vehicleRepository.findAll());
        return "employee/vehicle-list";
    }
}
