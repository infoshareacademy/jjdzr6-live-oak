package com.infoshareacademy.controller.employee;


import com.infoshareacademy.entity.ServiceOrder;
import com.infoshareacademy.service.ServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("add")
    public String getNewServiceOrder(Model model) {
        model.addAttribute("newServiceOrder", new ServiceOrder());
        return "employee/service-order-add";
    }

    @PostMapping("add")
    public String addNewServiceOrder(@ModelAttribute("newServiceOrder") ServiceOrder serviceOrder, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employee/service-order-add";
        }

        serviceOrderService.addServiceOrder(serviceOrder);
        return "redirect:/employee/service-orders";
    }

}
