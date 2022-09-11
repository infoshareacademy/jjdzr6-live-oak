package com.infoshareacademy.controller.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class SecurityController {
    @GetMapping("login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/403")
    @ResponseBody
    public String accessDenied() {
        // TODO: podpiąć plik html
        return "403 Forbidden";
    }
}
