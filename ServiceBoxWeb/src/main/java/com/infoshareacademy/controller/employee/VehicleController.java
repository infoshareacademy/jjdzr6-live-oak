package com.infoshareacademy.controller.employee;

import com.infoshareacademy.dto.serviceorder.CreateServiceOrderDto;
import com.infoshareacademy.dto.vehicle.VehicleDto;
import com.infoshareacademy.service.ServiceOrderService;
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

@RequestMapping("/employee/vehicles")
@RequiredArgsConstructor
@Controller
public class VehicleController {
    private final VehicleService vehicleService;
    private final ServiceOrderService serviceOrderService;

    @GetMapping
    public String getVehicles(Model model, @RequestParam(name = "s", required = false, defaultValue = "") String searchQuery) {
        if (searchQuery.isBlank()) {
            model.addAttribute("vehicles", vehicleService.findAll());
        } else {
            model.addAttribute("vehicles", vehicleService.findByQuery(searchQuery));
            model.addAttribute("searchQuery", searchQuery);
        }

        return "employee/vehicle-list";
    }


    @GetMapping("/{id}/create-service-order")
    public String newServiceOrderForm(Model model, @PathVariable("id") Long vehicleId) {
        VehicleDto vehicleDto = vehicleService.findById(vehicleId);

        if (vehicleDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        CreateServiceOrderDto orderDto = new CreateServiceOrderDto();
        orderDto.setOrderNumber(serviceOrderService.generateOrderNumber());

        model.addAttribute("serviceOrder", orderDto);
        model.addAttribute("vehicle", vehicleDto);
        return "employee/service-order-add";
    }

    @PostMapping("/{id}/create-service-order")
    public String newServiceOrder(
            @Valid @ModelAttribute("serviceOrder") CreateServiceOrderDto createServiceOrderDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @PathVariable("id") Long vehicleId,
            Model model
    ) {
        VehicleDto vehicleDto = vehicleService.findById(vehicleId);

        if (vehicleDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // check order number
        String orderNumber = createServiceOrderDto.getOrderNumber();
        if (serviceOrderService.isOrderExists(orderNumber)) {
            bindingResult.rejectValue("orderNumber", "orderNumber.exists",
                    "Zlecenie o numerze " + orderNumber + " już istnieje");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("vehicle", vehicleDto);
            return "employee/service-order-add";
        }

        vehicleService.createServiceOrder(vehicleId, createServiceOrderDto);
        redirectAttributes.addFlashAttribute("success", "Utworzono nowe zlecenie naprawy numer " +
                orderNumber);
        return "redirect:/employee/service-orders";
    }

    @GetMapping("/{id}")
    public String updateVehicle(@PathVariable("id") Long vehicleId, Model model) {
        VehicleDto vehicleDto = vehicleService.findById(vehicleId);
        model.addAttribute("vehicle", vehicleDto);
        return "employee/vehicle-update";
    }

    @PostMapping("/{id}/update")
    public String updateVehicle(
            @Valid @ModelAttribute("vehicle") VehicleDto vehicleDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @PathVariable("id") Long vehicleId,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("vehicle", vehicleDto);
            return "employee/vehicle-update";
        }

        vehicleService.updateVehicle(vehicleId, vehicleDto);
        redirectAttributes.addFlashAttribute("succes", "Zaktualizowano dane pojazdu.");
        return "redirect:/employee/vehicles";
    }
}

