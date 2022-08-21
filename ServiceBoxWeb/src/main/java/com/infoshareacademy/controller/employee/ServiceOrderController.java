package com.infoshareacademy.controller.employee;


import com.infoshareacademy.service.ServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("employee/")
public class ServiceOrderController {

    private final ServiceOrderService serviceOrderService;

    @Autowired
    public ServiceOrderController(ServiceOrderService serviceOrderService) {
        this.serviceOrderService = serviceOrderService;
    }


    @GetMapping("service-orders")

    public String getOrders(Model model) {
        model.addAttribute("serviceOrders", serviceOrderService.findAll());
        return "employee/service-order-list";
    }

}
