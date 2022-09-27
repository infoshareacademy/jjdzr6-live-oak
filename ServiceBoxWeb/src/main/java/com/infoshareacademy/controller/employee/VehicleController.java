package com.infoshareacademy.controller.employee;

import com.infoshareacademy.dto.vehicle.VehicleDto;
import com.infoshareacademy.entity.client.Client;
import com.infoshareacademy.entity.vehicle.Vehicle;
import com.infoshareacademy.service.ClientService;
import com.infoshareacademy.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;

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
    public String addNewVehicle(Model model, @RequestParam(name = "client", required = false, defaultValue = "0") Long clientId) {
        Client client = clientService.findClient(clientId);
        if (client == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Klient o id " + clientId + " nie istnieje");

        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setClientId(client.getId());
        vehicleDto.setClientName(client.getName());
        model.addAttribute("newVehicle", vehicleDto);
        return "employee/vehicle-add";
    }

    @PostMapping("add")
    public String addNewVehicle(
            @Valid @ModelAttribute("newVehicle") VehicleDto vehicleDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        // validate plate number
        String plateNumber = vehicleDto.getPlateNumber();
        if (vehicleService.plateNumberExists(plateNumber)) {
            bindingResult.rejectValue("plateNumber", "plateNumber.exists", "Pojazd o podanym numerze rejestracyjnym już istnieje");
        }

        // validate year
        if (vehicleDto.getProductionYear() != null) {
            Integer productionYear = vehicleDto.getProductionYear();
            if (productionYear < 1900 || productionYear > LocalDateTime.now().getYear()) {
                bindingResult.rejectValue("productionYear", "wrongYear", "Niepoprawny rok produkcji");
            }
        }

        if (bindingResult.hasErrors()) {
            return "employee/vehicle-add";
        }

        vehicleService.addVehicle(vehicleDto);
        redirectAttributes.addFlashAttribute("success", "Dodano nowy pojazd.");
        return "redirect:/employee/clients/" + vehicleDto.getClientId() + "/vehicles";
    }
}

