package com.infoshareacademy.controller.employee;

import com.infoshareacademy.dto.client.ClientDto;
import com.infoshareacademy.dto.client.CreateClientDto;
import com.infoshareacademy.dto.vehicle.CreateVehicleDto;
import com.infoshareacademy.dto.vehicle.VehicleDto;
import com.infoshareacademy.service.ClientService;
import com.infoshareacademy.service.VehicleService;
import com.infoshareacademy.utils.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RequestMapping("employee/clients")
@Controller
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final VehicleService vehicleService;

    @GetMapping
    public String getClients(Model model,
                             @RequestParam(name = "s", required = false, defaultValue = "") String searchQuery,
                             @PageableDefault(value = 5) @SortDefault("id") Pageable pageable) {
        if (searchQuery.isBlank()) {
            model.addAttribute("clients", clientService.findAll(pageable));
        } else {
            model.addAttribute("clients", clientService.findByQuery(searchQuery, pageable));
            model.addAttribute("searchQuery", searchQuery);
        }

        return "employee/client-list";
    }

    @GetMapping("new")
    public String newClientForm(Model model) {
        model.addAttribute("client", new CreateClientDto());
        return "employee/client-add";
    }

    @PostMapping("new")
    public String newClient(
            @Valid @ModelAttribute("client") CreateClientDto createClientDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        // check if email is unique
        String email = createClientDto.getEmail();
        if (clientService.isEmailExists(email)) {
            bindingResult.rejectValue("email", "email.exists", "Podany adres email już istnieje");
        }

        if (bindingResult.hasErrors()) {
            return "employee/client-add";
        }

        clientService.addClient(createClientDto);
        redirectAttributes.addFlashAttribute("success", "Dodano nowego klienta.");
        return "redirect:/employee/clients";
    }

    @GetMapping("/{id}/vehicles/add")
    public String addVehicleForm(Model model, @PathVariable("id") Long clientId) {
        ClientDto clientDto = clientService.findById(clientId);

        if (clientDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        model.addAttribute("vehicle", new CreateVehicleDto());
        model.addAttribute("client", clientDto);
        return "employee/vehicle-add";
    }

    @PostMapping("/{id}/vehicles/add")
    public String addVehicle(
            @Valid @ModelAttribute("vehicle") CreateVehicleDto createVehicleDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @PathVariable("id") Long clientId,
            Model model
    ) {
        ClientDto clientDto = clientService.findById(clientId);

        if (clientDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // check plate number
        String plateNumber = createVehicleDto.getPlateNumber();
        if (vehicleService.isPlateNumberExists(plateNumber)) {
            bindingResult.rejectValue("plateNumber", "plateNumber.exists", "Pojazd o podanym numerze rejestracyjnym już istnieje");
        }

        Integer productionYear = createVehicleDto.getProductionYear();
        // check year
        if (createVehicleDto.getProductionYear() != null) {
            if (productionYear < 1900 || productionYear > LocalDate.now().getYear()) {
                bindingResult.rejectValue("productionYear", "wrongYear", "Niepoprawny rok produkcji");
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("client", clientDto);
            return "employee/vehicle-add";
        }

        clientService.addVehicle(clientId, createVehicleDto);
        redirectAttributes.addFlashAttribute("success", "Dodano nowy pojazd.");
        return "redirect:/employee/clients/" + clientId + "/vehicles";
    }


    @GetMapping("/{id}/vehicles")
    public String getClientId(@PathVariable("id") Long clientId, Model model) {
        ClientDto clientDto = clientService.findById(clientId);

        if (clientDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<VehicleDto> clientVehicles = vehicleService.getClientVehicles(clientId);

        model.addAttribute("vehicles", clientVehicles);
        model.addAttribute("client", clientDto);

        return "employee/client-vehicle-list";
    }

    @GetMapping("/{id}/create-user-account")
    public String createUserAccount(@PathVariable("id") Long clientId, RedirectAttributes redirectAttributes) {
        ClientDto clientDto = clientService.findById(clientId);

        if (clientDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        String password = PasswordGenerator.generateRandomPassword(6);
        clientService.createUserAccount(clientId, password);

        redirectAttributes.addFlashAttribute("success", "Utworzono konto klienta. Login: <strong>" +
                clientDto.getEmail() +
                "</strong>, Hasło: <strong>" +
                password +
                "</strong>");

        return "redirect:/employee/clients";
    }

    @GetMapping("{id}")
    public String updateClient(@PathVariable("id") Long clientId, Model model) {
        ClientDto clientDto = clientService.findById(clientId);

        model.addAttribute("client", clientDto);
        return "employee/client-update";
    }

    @PostMapping("/{id}/update")
    public String updateClient(
            @Valid @ModelAttribute("client") ClientDto clientDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @PathVariable("id") Long clientId, Model model

    ) {

        if (bindingResult.hasErrors()) {
            return "employee/client-update";
        }

        clientService.updateClient(clientId, clientDto);

        redirectAttributes.addFlashAttribute("success", "Zaktualizowano dane klienta.");
        return "redirect:/employee/clients";
    }

}