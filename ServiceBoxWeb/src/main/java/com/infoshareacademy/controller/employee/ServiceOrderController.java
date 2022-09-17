package com.infoshareacademy.controller.employee;


import com.infoshareacademy.entity.Client;
import com.infoshareacademy.entity.ServiceOrder;
import com.infoshareacademy.entity.Vehicle;
import com.infoshareacademy.service.ClientService;
import com.infoshareacademy.service.ServiceOrderService;
import com.infoshareacademy.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

        model.addAttribute("vehicleService", vehicleService);
        return "employee/service-order-list";
    }

    @GetMapping("add")
    public String getNewServiceOrder(Model model, @RequestParam(name = "vehicle", required = false, defaultValue = "0") int vehicleId) {
        model.addAttribute("newServiceOrder", new ServiceOrder());
        model.addAttribute("vehicles", vehicleService.findAll());
        model.addAttribute("vid", vehicleId);
        return "employee/service-order-add";
    }

    @PostMapping("add")
    public String addNewServiceOrder(@Valid @ModelAttribute("newServiceOrder") ServiceOrder serviceOrder, BindingResult bindingResult, Model model, @RequestParam(name = "vehicle", required = false, defaultValue = "0") int vehicleId, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("vehicles", vehicleService.findAll());
            model.addAttribute("vid", vehicleId);
            return "employee/service-order-add";
        }

        serviceOrderService.addServiceOrder(serviceOrder);
        redirectAttributes.addFlashAttribute("success", "Utworzono nowe zlecenie naprawy.");
        return "redirect:/employee/service-orders";
    }

    @GetMapping("/{id}")
    public String getServiceOrderId(@PathVariable Integer id, Model model) {
        ServiceOrder serviceOrder = serviceOrderService.findServiceOrder(id);

        int vehicleId = serviceOrder.getVehicleId();
        Vehicle vehicleById = vehicleService.findVehicleById(vehicleId);

        int clientId = vehicleById.getClientId();
        Client clientById = clientService.findClientById(clientId);

        model.addAttribute("serviceOrderDetails", serviceOrder);
        model.addAttribute("vehicle", vehicleById);
        model.addAttribute("client", clientById);
        model.addAttribute("prevPath", "service-orders");
        return "employee/service-order-details";
    }

}
