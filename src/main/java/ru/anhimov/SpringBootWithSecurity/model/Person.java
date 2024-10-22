package ru.anhimov.SpringBootWithSecurity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "person")
@Data
@NoArgsConstructor
public class Person implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100, message = "Username should not be greater then 100 chars")
    @NotEmpty(message = "Username cannot be empty.")
    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @NotNull(message = "Year of birth cannot be null.")
    @Min(value = 1900, message = "Year of birth must be after 1900.")
    @Max(value = 2024, message = "Year of birth must be before or equal to 2024.")
    @Column(name = "year_of_birth", nullable = false)
    private Integer yearOfBirth;

    @NotEmpty(message = "Password cannot be empty.")
    @Column(name = "password", nullable = false)
    private String password;

    @NotEmpty(message = "Role cannot be empty.")
    @Column(name = "role", nullable = false)
    private String role;

    public Person(String username, String password, Integer yearOfBirth) {
        this(username, password, yearOfBirth, null);
    }

    public Person(String username, String password, Integer yearOfBirth, String role) {
        this.username = username;
        this.yearOfBirth = yearOfBirth;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", password='" + password + '\'' +
                '}';
    }
}