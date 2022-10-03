package com.infoshareacademy.controller.employee;


import com.infoshareacademy.service.ClientService;
import com.infoshareacademy.service.ServiceOrderService;
import com.infoshareacademy.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employee/service-orders")
@RequiredArgsConstructor
public class ServiceOrderController {
    private final ServiceOrderService serviceOrderService;
    private final VehicleService vehicleService;
    private final ClientService clientService;

    @GetMapping
    public String getOrders(Model model, @RequestParam(name = "s", required = false, defaultValue = "") String searchQuery) {
        if (searchQuery.isBlank()) {
            model.addAttribute("serviceOrders", serviceOrderService.findAll());
        } else {
            model.addAttribute("serviceOrders", serviceOrderService.findByQuery(searchQuery));
            model.addAttribute("searchQuery", searchQuery);
        }

        return "employee/service-order-list";
    }
}
