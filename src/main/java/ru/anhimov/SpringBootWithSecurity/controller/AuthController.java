package ru.anhimov.SpringBootWithSecurity.controller;

import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.anhimov.SpringBootWithSecurity.service.PeopleService;
import ru.anhimov.SpringBootWithSecurity.util.RegFormPersonValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PeopleService peopleService;
    private final PasswordEncoder passwordEncoder;
    private final RegFormPersonValidator regFormPersonValidator;

    public AuthController(PeopleService peopleService, PasswordEncoder passwordEncoder, RegFormPersonValidator regFormPersonValidator) {
        this.peopleService = peopleService;
        this.passwordEncoder = passwordEncoder;
        this.regFormPersonValidator = regFormPersonValidator;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("form") RegistrationForm form) {
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("form") @Valid RegistrationForm form,
                                  BindingResult bindingResult) {

        regFormPersonValidator.validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }
        peopleService.register(form.toPerson(passwordEncoder));
        return "redirect:/auth/login";
    }
}
