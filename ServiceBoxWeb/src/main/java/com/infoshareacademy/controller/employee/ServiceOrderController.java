package com.infoshareacademy.controller.employee;


import com.infoshareacademy.dto.serviceorder.ServiceOrderDto;
import com.infoshareacademy.entity.client.Client;
import com.infoshareacademy.entity.serviceorder.ServiceOrder;
import com.infoshareacademy.entity.vehicle.Vehicle;
import com.infoshareacademy.service.ClientService;
import com.infoshareacademy.service.ServiceOrderService;
import com.infoshareacademy.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/employee/service-orders")
public class ServiceOrderController {

    private final ServiceOrderService serviceOrderService;
    private final VehicleService vehicleService;

    private final ClientService clientService;

    @Autowired
    public ServiceOrderController(ServiceOrderService serviceOrderService, VehicleService vehicleService, ClientService clientService) {
        this.serviceOrderService = serviceOrderService;
        this.vehicleService = vehicleService;
        this.clientService = clientService;
    }

    @GetMapping
    public String getOrders(Model model, @RequestParam(name = "search", required = false, defaultValue = "") String searchQuery) {
        if (searchQuery.isBlank()) {
            model.addAttribute("serviceOrders", serviceOrderService.findAll());
        } else {
            model.addAttribute("serviceOrders", serviceOrderService.findByQuery(searchQuery));
            model.addAttribute("search", searchQuery);
        }

        return "employee/service-order-list";
    }

    @GetMapping("add")
    public String getNewServiceOrder(Model model, @RequestParam(name = "vehicle", required = false, defaultValue = "0") Long vehicleId) {
        Vehicle vehicle = vehicleService.findVehicle(vehicleId);
        if (vehicle == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pojazd o id " + vehicleId + " nie istnieje");

        ServiceOrderDto serviceOrderDto = new ServiceOrderDto();
        serviceOrderDto.setVehicleId(vehicle.getId());
        serviceOrderDto.setPlateNumber(vehicle.getPlateNumber());
        serviceOrderDto.setOrderNumber(serviceOrderService.generateOrderNumber());
        serviceOrderDto.setClientName(vehicle.getClient().getName());

        model.addAttribute("newServiceOrder", serviceOrderDto);
        return "employee/service-order-add";
    }

    @PostMapping("add")
    public String addNewServiceOrder(
            @Valid @ModelAttribute("newServiceOrder") ServiceOrderDto serviceOrderDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "employee/service-order-add";
        }

        serviceOrderService.addServiceOrder(serviceOrderDto);
        redirectAttributes.addFlashAttribute("success", "Utworzono nowe zlecenie naprawy.");
        return "redirect:/employee/service-orders";
    }

    @GetMapping("/{id}")
    public String getServiceOrderId(@PathVariable Integer id, Model model) {
        ServiceOrder serviceOrder = serviceOrderService.findServiceOrder(id);

        Vehicle vehicle = serviceOrder.getVehicle();
//        Client client = clientService.findClientById(clientId);

        model.addAttribute("serviceOrderDetails", serviceOrder);
        model.addAttribute("vehicle", vehicle);
//        model.addAttribute("client", client);
        model.addAttribute("prevPath", "service-orders");
        return "employee/service-order-details";
    }

}
