package com.infoshareacademy.controller.employee;

import com.infoshareacademy.entity.Client;
import com.infoshareacademy.entity.Vehicle;
import com.infoshareacademy.service.ClientService;
import com.infoshareacademy.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/employee/vehicles")
@Controller
public class VehicleController {
    private final ClientService clientService;
    private final VehicleService vehicleService;

    public VehicleController(ClientService clientService, VehicleService vehicleService) {
        this.clientService = clientService;
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

        model.addAttribute("clientService", clientService);

        return "employee/vehicle-list";
    }


    @GetMapping("add")
    public String addNewVehicle(Model model, @RequestParam(name = "client", required = false, defaultValue = "0") int clientId) {
        Client client = clientService.findClient(clientId);
        model.addAttribute("newVehicle", new Vehicle());
        model.addAttribute("client", client);
        return "employee/vehicle-add";
    }

    @PostMapping("add")
    public String addNewVehicle(@Valid @ModelAttribute("newVehicle") Vehicle vehicle, BindingResult bindingResult, Model model, @RequestParam(name = "client", required = false, defaultValue = "0") int clientId) {
        Client client = clientService.findClient(clientId);
        model.addAttribute("client", client);

        if (bindingResult.hasErrors()) {
            return "employee/vehicle-add";
        }

        vehicleService.addVehicle(vehicle);
        return "redirect:/employee/clients/" + clientId + "/vehicles";
    }
}

