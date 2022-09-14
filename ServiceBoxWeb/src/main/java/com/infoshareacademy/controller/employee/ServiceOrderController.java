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

import javax.validation.Valid;

@Controller
@RequestMapping("employee/")
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

    @GetMapping("service-orders")
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
    public String getNewServiceOrder(Model model) {
        model.addAttribute("newServiceOrder", new ServiceOrder());
        model.addAttribute("vehicles", vehicleService.findAll());
        return "employee/service-order-add";
    }

    @PostMapping("add")
    public String addNewServiceOrder(@Valid @ModelAttribute("newServiceOrder") ServiceOrder serviceOrder, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
          model.addAttribute("vehicles", vehicleService.findAll());
            return "employee/service-order-add";
        }

        serviceOrderService.addServiceOrder(serviceOrder);
        return "redirect:/employee/service-orders";
    }

    @GetMapping("service-orders/{id}")
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
