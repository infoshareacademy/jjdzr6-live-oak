package com.infoshareacademy.controller.employee;

import com.infoshareacademy.repository.ServiceOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("employee/")
public class ServiceOrderController {
    private final ServiceOrderRepository serviceOrderRepository;

    @Autowired
    public ServiceOrderController(ServiceOrderRepository serviceOrderRepository) {
        this.serviceOrderRepository = serviceOrderRepository;
    }

    @GetMapping("service-orders")

    public String getOrders(Model model){

        model.addAttribute("services",serviceOrderRepository.findAll());

        return "employee/service-order-list";
    }
}
