package ru.anhimov.SpringBootWithSecurity.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.anhimov.SpringBootWithSecurity.controller.RegistrationForm;
import ru.anhimov.SpringBootWithSecurity.model.Person;
import ru.anhimov.SpringBootWithSecurity.service.PeopleService;

import java.util.Optional;

@Component
public class RegFormPersonValidator implements Validator {
    private final PeopleService peopleService;

    @Autowired
    public RegFormPersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(RegistrationForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegistrationForm person = (RegistrationForm) target;
        Optional<Person> optionalPerson = peopleService.findByUsername(person.getUsername());
        if (optionalPerson.isPresent()) {
            errors.rejectValue("username", "personAlreadyExist", "Username already exists");
        }
    }
}
