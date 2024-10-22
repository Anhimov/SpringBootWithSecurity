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
@RequestMapping("/register")
public class RegisterController {
    private final PeopleService peopleService;
    private final PasswordEncoder passwordEncoder;
    private final RegFormPersonValidator regFormPersonValidator;

    public RegisterController(PeopleService peopleService, PasswordEncoder passwordEncoder, RegFormPersonValidator regFormPersonValidator) {
        this.peopleService = peopleService;
        this.passwordEncoder = passwordEncoder;
        this.regFormPersonValidator = regFormPersonValidator;
    }

    @GetMapping
    public String register(@ModelAttribute("form") RegistrationForm form) {
        return "register/register";
    }

    @PostMapping
    public String processRegister(@ModelAttribute("form") @Valid RegistrationForm form,
                                  BindingResult bindingResult) {

        regFormPersonValidator.validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            return "register/register";
        }
        peopleService.register(form.toPerson(passwordEncoder));
        return "redirect:/auth/login";
    }
}
