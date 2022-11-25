package com.infoshareacademy.controller;

import com.infoshareacademy.entity.serviceorder.ServiceOrderState;
import com.infoshareacademy.service.ServiceOrderService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PageController {
    private final ServiceOrderService orderService;

    public PageController(ServiceOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String startPage(Authentication auth) {
        if (auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_EMPLOYEE"))) {
            return "redirect:/employee";
        }

        return "redirect:/service-orders";
    }

    @GetMapping("/employee")
    public String employeeStartPage(Model model) {
        long[] stats = new long[3];
        stats[0] = orderService.countByState(ServiceOrderState.CREATED);
        stats[1] = orderService.countByState(ServiceOrderState.IN_PROGRESS);
        stats[2] = orderService.countByState(ServiceOrderState.FINISHED);

        model.addAttribute("stats", stats);
        model.addAttribute("lastServiceOrders", orderService.getLast3Orders());
        return "employee/start";
    }
}
