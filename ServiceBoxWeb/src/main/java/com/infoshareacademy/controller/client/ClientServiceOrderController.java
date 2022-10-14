package com.infoshareacademy.controller.client;

import com.infoshareacademy.service.ServiceOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class ClientServiceOrderController {

    private final ServiceOrderService serviceOrderService;

    @GetMapping("/service-orders")
    public String getOrders(Model model, Principal principal) {
        String email = principal.getName();
        model.addAttribute("serviceOrders", serviceOrderService.findServiceOrdersByClientEmail(email));
        return "client/client-service-order-list";
    }
}
