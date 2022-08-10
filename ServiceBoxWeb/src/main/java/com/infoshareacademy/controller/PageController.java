package com.infoshareacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String startPage() {
        return "redirect:/employee";
    }

    @GetMapping("/employee")
    public String employeeStartPage() {
        return "employee/start";
    }
}
