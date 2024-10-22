package ru.anhimov.SpringBootWithSecurity.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.anhimov.SpringBootWithSecurity.model.Person;
import ru.anhimov.SpringBootWithSecurity.service.PeopleService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final PeopleService peopleService;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(PeopleService peopleService, PasswordEncoder passwordEncoder) {
        this.peopleService = peopleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String register(@ModelAttribute("form") RegistrationForm form) {
        return "register/register";
    }

    @PostMapping
    public String processRegister(RegistrationForm form) {
        peopleService.save(form.toPerson(passwordEncoder));
        return "greeting/hello";
    }
}
