package com.bankmicroservicesapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    // настройка любой вкладки
    @GetMapping({"/home","/"})
    public String redirectHome() {
        return "home";
    }
    @GetMapping({"/info"})
    public String redirectInfo() {
        return "info";
    }
    @GetMapping({"/project-info"})
    public String redirectProjectInfo() {
        return "project";
    }
    @GetMapping({"/about"})
    public String redirectAboutMe() {
        return "about";
    }
    @GetMapping({"/jacoco"})
    public String redirectJacoco() {
        return "index";
    }
    @GetMapping({"/login"})
    public String redirectLogin() {
        return "login";
    }
    @GetMapping("/swagger")
    public String redirectSwagger() {
        return "redirect:/swagger-ui/index.html";
    }
}
