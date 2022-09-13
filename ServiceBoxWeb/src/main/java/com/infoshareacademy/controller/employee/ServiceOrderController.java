package com.infoshareacademy.controller.employee;


import com.infoshareacademy.entity.ServiceOrder;
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


    @Autowired
    public ServiceOrderController(ServiceOrderService serviceOrderService, VehicleService vehicleService) {
        this.serviceOrderService = serviceOrderService;
        this.vehicleService = vehicleService;
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
    public String addNewServiceOrder(@Valid @ModelAttribute("newServiceOrder") ServiceOrder serviceOrder, BindingResult bindingResult,  Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("vehicles", vehicleService.findAll());
            return "employee/service-order-add";
        }

        serviceOrderService.addServiceOrder(serviceOrder);
        return "redirect:/employee/service-orders";
    }

    @GetMapping("service-order/{id}")
    public String getServiceOrderId(@PathVariable Integer id, Model model){
        model.addAttribute("serviceOrderDetails", serviceOrderService.findId(id));
        model.addAttribute("prevPath", "service-orders");
        return "/employee/service-orders-details";
    }

}
