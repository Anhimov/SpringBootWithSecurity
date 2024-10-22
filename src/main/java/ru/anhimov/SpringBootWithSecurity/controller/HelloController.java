package ru.anhimov.SpringBootWithSecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.model.IModel;
import ru.anhimov.SpringBootWithSecurity.model.Person;

@Controller
@RequestMapping("/greeting")
public class HelloController {

    @GetMapping
    public String hello() {
        return "greeting/hello";
    }

    @GetMapping("/getUserInfo")
    public String getUserInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person principal = (Person) authentication.getPrincipal();
        model.addAttribute("principal", principal);
        return "greeting/getUserInfo";
    }
}
