package com.infoshareacademy.controller.employee;

import com.infoshareacademy.dto.client.ClientDto;
import com.infoshareacademy.entity.client.Client;
import com.infoshareacademy.entity.vehicle.Vehicle;
import com.infoshareacademy.service.ClientService;
import com.infoshareacademy.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("employee/")
@Controller
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final VehicleService vehicleService;

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

        // find client vehicles
        List<Vehicle> clientVehicles = vehicleService.getClientVehicles(client);

        model.addAttribute("vehicles", clientVehicles);
        model.addAttribute("client", client);
        return "employee/client-vehicle-list";
    }
}