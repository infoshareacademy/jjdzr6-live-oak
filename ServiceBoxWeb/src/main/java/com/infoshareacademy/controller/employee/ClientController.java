package com.infoshareacademy.controller.employee;

import com.infoshareacademy.dto.client.ClientDto;
import com.infoshareacademy.dto.vehicle.VehicleDto;
import com.infoshareacademy.entity.client.Client;
import com.infoshareacademy.entity.vehicle.Vehicle;
import com.infoshareacademy.mappers.vehicle.VehicleMapper;
import com.infoshareacademy.service.ClientService;
import com.infoshareacademy.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("employee/")
@Controller
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final VehicleService vehicleService;
    private final VehicleMapper vehicleMapper;

    @GetMapping("clients")
    public String getClients(Model model, @RequestParam(name = "search", required = false, defaultValue = "") String searchQuery) {
        if (searchQuery.isBlank()) {
            model.addAttribute("clients", clientService.findAll());
        } else {
            model.addAttribute("clients", clientService.findByQuery(searchQuery));
            model.addAttribute("search", searchQuery);
        }

        model.addAttribute("vehicleService", vehicleService);
        return "employee/client-list";
    }

    @GetMapping("addClient")
    public String getNewClient(Model model) {
        model.addAttribute("newClient", new ClientDto());
        return "employee/client-add";
    }

    @PostMapping("addClient")
    public String addNewClient(
            @Valid @ModelAttribute("newClient") ClientDto clientDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        // validate email
        String email = clientDto.getEmail();
        if (clientService.emailExists(email)) {
            bindingResult.rejectValue("email", "email.exists", "Podany adres email już istnieje");
        }

        if (bindingResult.hasErrors()) {
            return "employee/client-add";
        }

        clientService.addClient(clientDto);
        redirectAttributes.addFlashAttribute("success", "Dodano nowego klienta.");
        return "redirect:/employee/clients";
    }

    @GetMapping("/clients/{id}/vehicles")
    public String getClientId(@PathVariable Integer id, Model model) {
        Client client = clientService.findClient(id);

        if (client == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Klient o id " + id + " nie istnieje");
        List<VehicleDto> vehicles = client.getVehicles().stream()
                .map(vehicleMapper::toDto)
                .toList();

        model.addAttribute("vehicles", vehicles);
        model.addAttribute("clientId", client.getId());
        model.addAttribute("clientName", client.getName());
        return "employee/client-vehicle-list";
    }
}