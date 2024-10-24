package ru.anhimov.SpringBootWithSecurity.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.anhimov.SpringBootWithSecurity.model.Person;
import ru.anhimov.SpringBootWithSecurity.service.PeopleService;
import ru.anhimov.SpringBootWithSecurity.util.RegFormPersonValidator;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PeopleService peopleService;
    private final PasswordEncoder passwordEncoder;
    private final RegFormPersonValidator regFormPersonValidator;

    public AdminController(PeopleService peopleService, PasswordEncoder passwordEncoder, RegFormPersonValidator regFormPersonValidator) {
        this.peopleService = peopleService;
        this.passwordEncoder = passwordEncoder;
        this.regFormPersonValidator = regFormPersonValidator;
    }

    @GetMapping()
    public String getAdminPanel(Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("people", peopleService.findAllPeople());
        return "admin/panel";
    }

    @PatchMapping("/add")
    public String changePersonRole(@ModelAttribute("person") Person person) {
        peopleService.changePersonRole(person.getId(), person.getRole());
        return "redirect:/admin";
    }
}
