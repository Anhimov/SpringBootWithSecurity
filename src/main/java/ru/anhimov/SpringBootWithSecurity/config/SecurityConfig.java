package ru.anhimov.SpringBootWithSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.anhimov.SpringBootWithSecurity.model.Person;
import ru.anhimov.SpringBootWithSecurity.repositories.PeopleRepository;

import java.util.Optional;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PeopleRepository peopleRepository) {
        return username -> {
            Optional<Person> byUsername = peopleRepository.findByUsername(username);
            if (byUsername.isPresent()) return (UserDetails) byUsername.get();

            throw new UsernameNotFoundException(username);
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/login", "/auth/register", "/error").permitAll()
                        .requestMatchers("/greeting/getUserInfo").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/greeting/**").authenticated()
                        .anyRequest().hasAnyRole("ADMIN", "USER")
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/process_login")
                        .defaultSuccessUrl("/greeting", true)
                        .failureUrl("/auth/login?error")
                )
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/auth/login")
                )
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )
                .build();
    }
}
