package com.infoshareacademy.controller.client;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

public class ServiceOrderController {

    @GetMapping
    public String getOrders(Model model, Principal principal) {

        String userName = principal.getName();
//        model.addAttribute("serviceOrders", serviceOrderService.findByCriteria(searchQuery, filter));

        return "client/client-service-order-list";
    }

}
