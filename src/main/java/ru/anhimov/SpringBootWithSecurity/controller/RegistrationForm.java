package ru.anhimov.SpringBootWithSecurity.controller;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.anhimov.SpringBootWithSecurity.model.Person;

@Data
public class RegistrationForm {
    private String username;
    private Integer yearOfBirth;
    private String password;
    private String role;

    public Person toPerson(PasswordEncoder passwordEncoder) {
        return new Person(username, passwordEncoder.encode(password), yearOfBirth);
    }
}
