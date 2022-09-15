package com.infoshareacademy.controller.employee;

import com.infoshareacademy.entity.Client;
import com.infoshareacademy.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("employee/")
@Controller
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("clients")
    public String getClients(Model model, @RequestParam(name = "search", required = false, defaultValue = "") String searchQuery) {
        if (searchQuery.isBlank()) {
            model.addAttribute("clients", clientService.findAll());
        } else {
            model.addAttribute("clients", clientService.findByQuery(searchQuery));
            model.addAttribute("search", searchQuery);
        }

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
}