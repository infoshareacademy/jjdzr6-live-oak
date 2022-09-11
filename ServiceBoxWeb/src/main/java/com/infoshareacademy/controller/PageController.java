package com.infoshareacademy.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.infoshareacademy.entity.UserRole.EMPLOYEE;

@Controller
public class PageController {
    @GetMapping("/")
    public String startPage(Authentication auth) {
        if (auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(EMPLOYEE.getRoleNameWithPrefix()))) {
            return "redirect:/employee";
        }

        return "client/start";
    }

    @GetMapping("/employee")
    public String employeeStartPage() {
        return "employee/start";
    }
}
