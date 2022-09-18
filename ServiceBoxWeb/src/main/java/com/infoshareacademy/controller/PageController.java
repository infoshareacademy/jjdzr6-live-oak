package com.infoshareacademy.controller;

import com.infoshareacademy.entity.serviceorder.ServiceOrderState;
import com.infoshareacademy.service.ServiceOrderService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.infoshareacademy.entity.user.UserRole.EMPLOYEE;

@Controller
public class PageController {
    private final ServiceOrderService orderService;

    public PageController(ServiceOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String startPage(Authentication auth) {
        if (auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(EMPLOYEE.getRoleNameWithPrefix()))) {
            return "redirect:/employee";
        }

        return "client/start";
    }

    @GetMapping("/employee")
    public String employeeStartPage(Model model) {
        long[] stats = new long[4];
        stats[0] = orderService.countByState(ServiceOrderState.CREATED);
        stats[1] = orderService.countByState(ServiceOrderState.WAITING_FOR_PARTS);
        stats[2] = orderService.countByState(ServiceOrderState.IN_PROGRESS);
        stats[3] = orderService.countByState(ServiceOrderState.FINISHED);

        model.addAttribute("stats", stats);
        model.addAttribute("lastOrder", orderService.getLastOrder().orElse(null));
        return "employee/start";
    }
}
