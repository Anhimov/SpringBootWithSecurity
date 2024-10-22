package ru.anhimov.SpringBootWithSecurity.controller;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.anhimov.SpringBootWithSecurity.model.Person;

@Data
public class RegistrationForm {
    @Size(max = 100)
    @NotEmpty(message = "Username cannot be empty.")
    private String username;
    @NotNull(message = "Year of birth cannot be null.")
    @Min(value = 1900, message = "Year of birth must be after 1900.")
    @Max(value = 2024, message = "Year of birth must be before or equal to 2024.")
    private Integer yearOfBirth;
    @NotEmpty(message = "Password cannot be empty.")
    private String password;
    private String role;

    public Person toPerson(PasswordEncoder passwordEncoder) {
        return new Person(username, passwordEncoder.encode(password), yearOfBirth);
    }
}
