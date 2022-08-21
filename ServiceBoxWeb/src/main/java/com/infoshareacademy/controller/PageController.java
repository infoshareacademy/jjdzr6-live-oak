package com.infoshareacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

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

    @GetMapping("/template")
    public String template(Model model) {
        ArrayList<String> vehicles = new ArrayList<>();
        model.addAttribute("lista", vehicles);
        return "template";
    }
}
