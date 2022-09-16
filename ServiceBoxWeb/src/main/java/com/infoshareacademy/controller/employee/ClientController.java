package com.infoshareacademy.controller.employee;

import com.infoshareacademy.entity.Client;
import com.infoshareacademy.entity.ServiceOrder;
import com.infoshareacademy.entity.Vehicle;
import com.infoshareacademy.service.ClientService;
import com.infoshareacademy.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("employee/")
public class ClientController {

    private final ClientService clientService;
    private final VehicleService vehicleService;

    @Autowired
    public ClientController(ClientService clientService, VehicleService vehicleService) {
        this.clientService = clientService;
        this.vehicleService = vehicleService;
    }

    @GetMapping("clients")

    public String getClients(Model model){
        model.addAttribute("clients",clientService.findAll());

        return "employee/client-list";
    }

    @GetMapping("addClient")
    public String getNewClient(Model model) {
        model.addAttribute("newClient", new Client());
        return "employee/client-add";
    }

    @PostMapping("addClient")
    public String addNewClient(@Valid @ModelAttribute("newClient") Client client, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "employee/client-add";
        }

        clientService.addClient(client);
        return "redirect:/employee/clients";
    }

    @GetMapping("/{id}")
    public String getClientId(@PathVariable Integer id, Model model) {
        Client client = clientService.findClient(id);

        int vehicleId = client.getVehicleId();
        Vehicle vehicleById = vehicleService.findVehicleById(vehicleId);

        model.addAttribute("clientVehicles", client);
        model.addAttribute("vehicle", vehicleById);
        model.addAttribute("prevPath", "clients");
        return "employee/client-vehicle-list";
    }
}